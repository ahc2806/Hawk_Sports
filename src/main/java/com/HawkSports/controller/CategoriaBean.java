package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.HawkSports.dao.CategoriaDAO;
import com.HawkSports.model.Categoria;

@Named
@RequestScoped
public class CategoriaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Categoria> listaCategorias = new ArrayList<Categoria>();

    public List<Categoria> getListaCategorias() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        listaCategorias = categoriaDAO.consultar();
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    public String nuevo() {
		Categoria categoria = new Categoria();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("categoria", categoria);
		return "agregar.xhtml?faces-redirect=true\"";
	}
    public String agregar(Categoria categoria) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoria.setEstado(true);
		categoriaDAO.agregar(categoria);
		return "categorias.xhtml?faces-redirect=true\"";
	}
    
    public String editar(Short idCategoria) {
    	CategoriaDAO categoriaDAO = new CategoriaDAO();
    	Categoria categoria = new Categoria();
    	categoria = categoriaDAO.consultarId(idCategoria);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("categoria", categoria);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Categoria categoria) {
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		categoriaDAO.editar(categoria);
		return "categorias.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idCategoria) {	
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		Categoria categoria = new Categoria();
		categoria = categoriaDAO.consultarId(idCategoria);
		categoria.setEstado(false);		
		categoriaDAO.editar(categoria);
		return "categorias.xhtml?faces-redirect=true\"";
	}
}