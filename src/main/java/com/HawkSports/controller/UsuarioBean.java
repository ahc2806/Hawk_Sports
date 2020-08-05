package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.HawkSports.dao.UsuarioDAO;
import com.HawkSports.model.Usuario;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private String usuario;
    private String contrasena;

    public List<Usuario> getListaUsuarios() {
    	UsuarioDAO usuarioDAO = new UsuarioDAO();
        listaUsuarios = usuarioDAO.consultar();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String validar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean valido = usuarioDAO.esValido(usuario, contrasena);
        if (valido) {
            HttpSession session = SesionUtils.getSession();
            session.setAttribute("usuario", usuario);
            session.setAttribute("contrasena", contrasena);
            return "components/menu/menu.xhtml?faces-redirect=true\"";
        } else {
        	return "/index.xhtml?faces-redirect=true\"";
        }
    }

    public String cerrar() {
        HttpSession session = SesionUtils.getSession();
        session.invalidate();
        return "/index.xhtml?faces-redirect=true\"";
    }

    public String nuevo() {
		Usuario usuario = new Usuario();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", usuario);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario.setEstado(true);
		usuarioDAO.agregar(usuario);
		return "usuarios.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idUsuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.consultarId(idUsuario);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("usuario", usuario);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.editar(usuario);
		return "usuarios.xhtml?faces-redirect=true\"";
	}
	
	public String eliminar(Short idUsuario) {	
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		usuario = usuarioDAO.consultarId(idUsuario);
		usuario.setEstado(false);		
		usuarioDAO.editar(usuario);
		return "usuarios.xhtml?faces-redirect=true\"";
	}
}