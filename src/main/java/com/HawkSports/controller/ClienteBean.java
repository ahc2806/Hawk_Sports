package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.HawkSports.dao.ClienteDAO;
import com.HawkSports.dao.DireccionDAO;
import com.HawkSports.model.Cliente;
import com.HawkSports.model.Direccion;

@Named
@RequestScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Cliente> listaClientes = new ArrayList<Cliente>();

    public List<Cliente> getListaClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();
        listaClientes = clienteDAO.consultar();
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
    public String nuevo() {
		Direccion direccion = new Direccion();
		Cliente cliente = new Cliente();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("cliente", cliente);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Cliente cliente, Direccion direccion){
		DireccionDAO direccionDAO = new DireccionDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		direccion.setEstado(true);
		direccionDAO.agregar(direccion);
		Short idDireccion = direccionDAO.ultimo_id();
		cliente.setIdDireccion(idDireccion);
		cliente.setEstado(true);
		clienteDAO.agregar(cliente);
		return "clientes.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idCliente, Short idDireccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		Direccion direccion = new Direccion();
		Cliente cliente = new Cliente();
		direccion = direccionDAO.consultarId(idDireccion);
		cliente = clienteDAO.consultarId(idCliente);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("cliente", cliente);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Cliente cliente, Direccion direccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		direccionDAO.editar(direccion);
		clienteDAO.editar(cliente);
		return "clientes.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idCliente) {	
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente();
		cliente = clienteDAO.consultarId(idCliente);
		cliente.setEstado(false);		
		clienteDAO.editar(cliente);
		return "clientes.xhtml?faces-redirect=true\"";
	}
}