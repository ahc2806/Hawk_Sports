package com.HawkSports.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private Short idProveedor;
	@Column
	private String rfc;
	@Column
	private String nombre;
	@Column(name = "apellido_p")
	private String apellidoP;
	@Column(name = "apellido_m")
	private String apellidoM;
	@Column
	private String telefono;
	@Column(name = "correo_e")
	private String correoE;
	@Column
	private boolean estado;
	@Column(name = "id_direccion")
	private Short idDireccion;

	public Short getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Short idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoP() {
		return apellidoP;
	}

	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}

	public String getApellidoM() {
		return apellidoM;
	}

	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreoE() {
		return correoE;
	}

	public void setCorreoE(String correoE) {
		this.correoE = correoE;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Short getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Short idDireccion) {
		this.idDireccion = idDireccion;
	}

	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", rfc=" + rfc + ", nombre=" + nombre + ", apellidoP="
				+ apellidoP + ", apellidoM=" + apellidoM + ", telefono=" + telefono + ", correoE=" + correoE
				+ ", estado=" + estado + ", idDireccion=" + idDireccion + "]";
	}
}
