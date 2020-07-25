package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.HawkSports.dao.ProveedorDAO;
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
}