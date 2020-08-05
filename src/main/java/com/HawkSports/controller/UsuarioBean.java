package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.HawkSports.dao.DireccionDAO;
import com.HawkSports.dao.UsuarioDAO;
import com.HawkSports.model.Direccion;
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
		Direccion direccion = new Direccion();
		Usuario usuario = new Usuario();
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("usuario", usuario);
		return "agregar.xhtml?faces-redirect=true\"";
	}

	public String agregar(Usuario usuario, Direccion direccion){
		DireccionDAO direccionDAO = new DireccionDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		direccion.setEstado(true);
		direccionDAO.agregar(direccion);
		Short idDireccion = direccionDAO.ultimo_id();
		usuario.setIdDireccion(idDireccion);
		usuario.setEstado(true);
		usuarioDAO.agregar(usuario);
		return "usuarios.xhtml?faces-redirect=true\"";
	}
	
	public String editar(Short idUsuario, Short idDireccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Direccion direccion = new Direccion();
		Usuario usuario = new Usuario();
		direccion = direccionDAO.consultarId(idDireccion);
		usuario = usuarioDAO.consultarId(idUsuario);
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("direccion", direccion);
		sessionMap.put("usuario", usuario);
		return "editar.xhtml?faces-redirect=true\"";
	}

	public String actualizar(Usuario usuario, Direccion direccion) {
		DireccionDAO direccionDAO = new DireccionDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		direccionDAO.editar(direccion);
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


