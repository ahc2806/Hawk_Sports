package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.HawkSports.dao.EmpleadoDAO;
import com.HawkSports.model.Empleado;

@Named
@RequestScoped
public class EmpleadoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private EmpleadoDAO empleado;
	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();

	public List<Empleado> getListaEmpleados() {
		empleado = new EmpleadoDAO();
		listaEmpleados = empleado.consultar();
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
}