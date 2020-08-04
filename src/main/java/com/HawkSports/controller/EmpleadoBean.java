package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.HawkSports.dao.DireccionDAO;
import com.HawkSports.dao.EmpleadoDAO;
import com.HawkSports.model.Direccion;
import com.HawkSports.model.Empleado;

@Named
@RequestScoped
public class EmpleadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();

	public List<Empleado> getListaEmpleados() {
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		listaEmpleados = empleadoDAO.consultar();
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	
	public String nuevo() {
		Direccion direccion = new Direccion();
		Empleado empleado = new Empleado();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("empleado", empleado);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Empleado empleado, Direccion direccion){
		DireccionDAO direccionDAO = new DireccionDAO();
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		direccion.setEstado(true);
		direccionDAO.agregar(direccion);
		Short idDireccion = direccionDAO.ultimo_id();
		empleado.setIdDireccion(idDireccion);
		empleado.setEstado(true);
		empleadoDAO.agregar(empleado);
		return "empleados.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idEmpleado, Short idDireccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		Direccion direccion = new Direccion();
		Empleado empleado = new Empleado();
		direccion = direccionDAO.consultarId(idDireccion);
		empleado = empleadoDAO.consultarId(idEmpleado);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("empleado", empleado);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Empleado empleado, Direccion direccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		direccionDAO.editar(direccion);
		empleadoDAO.editar(empleado);
		return "empleados.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idEmpleado) {	
		EmpleadoDAO empleadoDAO = new EmpleadoDAO();
		Empleado empleado = new Empleado();
		empleado = empleadoDAO.consultarId(idEmpleado);
		empleado.setEstado(false);		
		empleadoDAO.editar(empleado);
		return "empleados.xhtml?faces-redirect=true\"";
	}
}