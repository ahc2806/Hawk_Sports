package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.HawkSports.dao.DireccionDAO;
import com.HawkSports.dao.ProveedorDAO;
import com.HawkSports.model.Direccion;
import com.HawkSports.model.Proveedor;

@Named
@RequestScoped
public class ProveedorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ProveedorDAO proveedor;
	private List<Proveedor> listaProveedores = new ArrayList<Proveedor>();

	public List<Proveedor> getListaProveedores() {
		proveedor = new ProveedorDAO();
		listaProveedores = proveedor.consultar();
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	public String nuevo() {
		Direccion direccion = new Direccion();
		Proveedor proveedor = new Proveedor();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("proveedor", proveedor);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Proveedor proveedor, Direccion direccion){
		DireccionDAO direccionDAO = new DireccionDAO();
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		direccion.setEstado(true);
		direccionDAO.agregar(direccion);
		Short idDireccion = direccionDAO.ultimo_id();
		proveedor.setIdDireccion(idDireccion);
		proveedor.setEstado(true);
		proveedorDAO.agregar(proveedor);
		return "proveedores.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idProveedor, Short idDireccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		Direccion direccion = new Direccion();
		Proveedor proveedor= new Proveedor();
		direccion = direccionDAO.consultarId(idDireccion);
		proveedor = proveedorDAO.consultarId(idProveedor);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("proveedor", proveedor);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Proveedor proveedor, Direccion direccion) {
		DireccionDAO direccionDAO =new DireccionDAO();
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		direccionDAO.editar(direccion);
		proveedorDAO.editar(proveedor);
		return "proveedores.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idProveedor) {	
		ProveedorDAO proveedorDAO = new ProveedorDAO();
		Proveedor proveedor = new Proveedor();
		proveedor = proveedorDAO.consultarId(idProveedor);
		proveedor.setEstado(false);		
		proveedorDAO.editar(proveedor);
		return "empleados.xhtml?faces-redirect=true\"";
	}
}