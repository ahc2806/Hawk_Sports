package com.HawkSports.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LogueoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String nombre;
	
	public String getUsuario() {
		usuario = SesionUtils.getUserName();
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		nombre = SesionUtils.getFullName();
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}