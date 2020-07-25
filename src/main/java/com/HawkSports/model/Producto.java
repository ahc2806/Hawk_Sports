package com.HawkSports.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Named
@RequestScoped
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Inidica que se autoincrementa
	@Column(name = "id_producto") // Indica que así aparece en la BD
	private Short idProducto;
	@Column
	private String sku;
	@Column
	private String descripcion;
	@Column(name = "precio_compra")
	private double precioCompra;
	@Column(name = "precio_venta")
	private double precioVenta;
	@Column
	private int existencia;
	@Column
	private String imagen;
	@Column
	private boolean estado;
	@Column(name = "id_categoria")
	private Short idCategoria;

	public Short getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Short idProducto) {
		this.idProducto = idProducto;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Short getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Short idCategoria) {
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", sku=" + sku + ", descripcion=" + descripcion
				+ ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", existencia=" + existencia
				+ ", imagen=" + imagen + ", estado=" + estado + ", idCategoria=" + idCategoria + "]";
	}
}
