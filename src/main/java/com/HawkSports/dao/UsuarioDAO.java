package com.HawkSports.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Usuario;

public class UsuarioDAO {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    Usuario usuario = new Usuario();

    public void guardar(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUtil.shutdown();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> consultar() {
        try {
            List<Usuario> lista_usuarios;
            Query query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.estado = TRUE");
            lista_usuarios = query.getResultList();
            Iterator<Usuario> iterator = lista_usuarios.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            return lista_usuarios;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario consultarId(Short idUsuario) {
        try {
            usuario = entityManager.find(Usuario.class, idUsuario);
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JPAUtil.shutdown();
        }
    }

    public void editar(Usuario usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUtil.shutdown();
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
        } finally {
            JPAUtil.shutdown();
        }
    }

    public void pre_eliminar(Short idUsuario) {
        try {
            usuario = entityManager.find(Usuario.class, idUsuario);
            entityManager.getTransaction().begin();
            usuario.setEstado(false);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JPAUtil.shutdown();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean esValido(String usuario, String contrasena) {
        System.out.println("esValido() " + usuario + "" + contrasena);
        Query query = entityManager.createQuery(
                "SELECT u.usuario, u.contrasena FROM Usuario u WHERE u.usuario LIKE :usuario and u.contrasena LIKE :contrasena");
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);

        List<String> dato = query.getResultList();
        if (!dato.isEmpty()) {
            System.out.println("Datos correctos");
            return true;
        } else {
        	return false;
        }
    }
}







