package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.HawkSports.dao.ProductoDAO;
import com.HawkSports.model.Producto;

@Named
@RequestScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Producto> listaProductos = new ArrayList<Producto>();

	public List<Producto> getListaProductos() {
		ProductoDAO productoDAO = new ProductoDAO();
		listaProductos = productoDAO.consultar();
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public String nuevo() {
		Producto producto = new Producto();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("producto", producto);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		producto.setEstado(true);
		productoDAO.agregar(producto);
		return "productos.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idProducto) {
		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = new Producto();
		producto = productoDAO.consultarId(idProducto);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("producto", producto);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.editar(producto);
		return "productos.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idProducto) {	
		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = new Producto();
		producto = productoDAO.consultarId(idProducto);
		producto.setEstado(false);		
		productoDAO.editar(producto);
		return "productos.xhtml?faces-redirect=true\"";
	}
}