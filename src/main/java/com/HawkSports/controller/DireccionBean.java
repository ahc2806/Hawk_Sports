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
	private List<Direccion> listaDirecciones = new ArrayList<Direccion>();

	public List<Direccion> getListaDirecciones() {
		DireccionDAO direccionDAO = new DireccionDAO();
		listaDirecciones = direccionDAO.consultar();
		return listaDirecciones;
	}

	public void setListaDireciones(List<Direccion> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}
}