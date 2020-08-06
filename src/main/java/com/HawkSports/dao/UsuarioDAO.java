package com.HawkSports.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Usuario;

public class UsuarioDAO {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    Usuario usuario = new Usuario();

    public void agregar(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> consultar() {
        try {
            List<Usuario> lista_usuarios;
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.estado = TRUE");
            lista_usuarios = query.getResultList();
            return lista_usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Usuario> consultarPapelera() {
        try {
            List<Usuario> lista_usuarios;
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.estado = FALSE");
            lista_usuarios = query.getResultList();
            return lista_usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario consultarId(Short idUsuario) {
        try {
            usuario = entityManager.find(Usuario.class, idUsuario);
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("desencriptar_contrasena");
            query.registerStoredProcedureParameter(1, Short.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
            query.setParameter(1, idUsuario);
            query.execute();
            usuario.setContrasena((String) query.getOutputParameterValue(2));
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editar(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(Short idUsuario) {
        try {
            usuario = entityManager.find(Usuario.class, idUsuario);
            entityManager.getTransaction().begin();
            entityManager.remove(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean esValido(String usuario, String contrasena) {
        Query query = entityManager.createQuery("SELECT u.usuario, u.contrasena "
        									+ "FROM Usuario u "
        									+ "WHERE u.usuario = :usuario "
        									+ "AND u.contrasena = HEX(AES_ENCRYPT(:contrasena, 'HawkSports')) "
        									+ "AND u.estado = TRUE");
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);
        List<String> dato = query.getResultList();
        if (!dato.isEmpty()) 
            return true;
         else 
        	return false;
    }
    
    public String nombre_usuario_logueado(String usuario, String contrasena) {
    	StoredProcedureQuery query = entityManager.createStoredProcedureQuery("nombre_usuario_logueado");
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
        query.setParameter(1, usuario);
        query.setParameter(2, contrasena);
        query.execute();
        String full_name = (String) query.getOutputParameterValue(3);
        return full_name;
    }
}