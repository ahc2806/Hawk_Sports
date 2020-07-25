package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.HawkSports.dao.ClienteDAO;
import com.HawkSports.model.Cliente;

@Named
@RequestScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ClienteDAO cliente;
    private List<Cliente> listaClientes = new ArrayList<Cliente>();

    public List<Cliente> getListaClientes() {
        cliente = new ClienteDAO();
        listaClientes = cliente.consultar();
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}

