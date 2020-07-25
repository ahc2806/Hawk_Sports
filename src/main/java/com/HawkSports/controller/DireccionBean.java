package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.HawkSports.dao.DireccionDAO;
import com.HawkSports.model.Direccion;

@Named
@RequestScoped
public class DireccionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private DireccionDAO direccion;
	private List<Direccion> listaDirecciones = new ArrayList<Direccion>();

	public List<Direccion> getListaDirecciones() {
		direccion = new DireccionDAO();
		listaDirecciones = direccion.consultar();
		return listaDirecciones;
	}

	public void setListaDireciones(List<Direccion> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}
}