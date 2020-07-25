package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.HawkSports.dao.ProductoDAO;
import com.HawkSports.model.Producto;

@Named
@RequestScoped
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Producto> listaProductos = new ArrayList<Producto>();
	private Part img;

	public List<Producto> getListaProductos() {
		ProductoDAO productoDAO = new ProductoDAO();
		listaProductos = productoDAO.consultar();
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Part getImg() {
		return img;
	}

	public void setImg(Part img) {
		this.img = img;
	}
	
	public String nuevo() {
		Producto producto = new Producto();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("producto", producto);
		return "agregar_producto(); titulo('Nuevo Producto');";
	}

	public String agregar(Producto producto) {
		ProductoDAO productoDAO = new ProductoDAO();
		producto.setEstado(true);
		producto.setImagen(img.getSubmittedFileName());
		productoDAO.agregar(producto);
		return "producto()";
	}

	public String editar(Short idProducto) {
		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = new Producto();
		producto = productoDAO.consultarId(idProducto);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("producto", producto);
		return "editar_producto();";
	}

	public String actualizar(Producto producto) {
		if (img != null) {
			producto.setImagen(img.getSubmittedFileName());
		}
		ProductoDAO productoDAO = new ProductoDAO();
		productoDAO.editar(producto);
		return "allProducts";
	}
	
	public void eliminar(Short idProducto) {	
		ProductoDAO productoDAO = new ProductoDAO();
		Producto producto = new Producto();
		producto = productoDAO.consultarId(idProducto);
		producto.setEstado(false);		
		productoDAO.editar(producto);
	}
}