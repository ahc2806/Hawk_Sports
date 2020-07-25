package com.HawkSports.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.HawkSports.dao.UsuarioDAO;
import com.HawkSports.model.Usuario;

@Named
@RequestScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private UsuarioDAO usuarioDAO;
    private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    private String usuario;
    private String contrasena;
    public static final String LOGOUT_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";

    public List<Usuario> getListaUsuarios() {
        usuarioDAO = new UsuarioDAO();
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
        System.out.println("Validar(): " + usuario + "" + contrasena);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean valido = usuarioDAO.esValido(usuario, contrasena);
        if (valido) {
            HttpSession session = SesionUtils.getSession();
            session.setAttribute("usuario", usuario);
            return "components/menu/menu";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    public String cerrar() {
        System.out.println("Cerrando");
        HttpSession session = SesionUtils.getSession();
        session.invalidate();
        return LOGOUT_PAGE_REDIRECT;
    }
}


