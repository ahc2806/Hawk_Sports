package com.HawkSports.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.Categoria;
import com.HawkSports.model.JPAUtil;

public class CategoriaDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Categoria categoria = new Categoria();
	
	public void guardar(Categoria categoria) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> consultar() {
		try {
			List<Categoria> lista_categorias;
			Query query = entityManager.createQuery("SELECT c FROM Categoria c WHERE c.estado = TRUE");
			lista_categorias = query.getResultList();
			return lista_categorias;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Categoria consultarId(Short idCategoria) {
		try {
			categoria = entityManager.find(Categoria.class, idCategoria);
			return categoria;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void editar(Categoria categoria) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void eliminar(Short idCategoria) {
		try {
			categoria = entityManager.find(Categoria.class, idCategoria);
			entityManager.getTransaction().begin();
			entityManager.remove(categoria);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void pre_eliminar(Short idCategoria) {
		try {
			categoria = entityManager.find(Categoria.class, idCategoria);
			entityManager.getTransaction().begin();
			categoria.setEstado(false);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> obtenerNombres() {
		List<Categoria> listaNombres;
		Query query = entityManager.createQuery("SELECT c.nombre FROM Categoria c WHERE c.estado = TRUE");
		listaNombres = query.getResultList();
		return listaNombres;
	}
}
