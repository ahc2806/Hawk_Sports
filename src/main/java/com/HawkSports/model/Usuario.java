package com.HawkSports.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Short idUsuario;
    @Column
    private String usuario;
    @Column
    private String contrasena;
    @Column
    private String imagen;
    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;
    @Column
    private String tipo;
    @Column
    private boolean estado;
    @Column(name = "id_direccion")
	private Short idDireccion;
	

    public Short getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Short idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public Short getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Short idDireccion) {
		this.idDireccion = idDireccion;
	}

    @Override
    public String toString() {
        return "UsuarioBean [idUsuario=" + idUsuario + ", usuario=" + usuario + ", contrasena=" + contrasena
                + ", imagen=" + imagen + ", fechaCreacion=" + fechaCreacion + ", tipo=" + tipo + ", estado=" + estado
                + "]";
    }
}




