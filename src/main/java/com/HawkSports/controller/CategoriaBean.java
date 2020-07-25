package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import com.HawkSports.dao.CategoriaDAO;
import com.HawkSports.model.Categoria;

@Named
@RequestScoped
public class CategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private CategoriaDAO categoriaDAO;
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();

    public List<Categoria> getListaCategorias() {
        categoriaDAO = new CategoriaDAO();
        listaCategorias = categoriaDAO.consultar();
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
}

