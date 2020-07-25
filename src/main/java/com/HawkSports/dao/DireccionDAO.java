package com.HawkSports.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Direccion;

public class DireccionDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Direccion direccion = new Direccion();
	
	public void guardar(Direccion direccion) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(direccion);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Direccion> consultar() {
		try {
			List<Direccion> lista_direcciones;
			Query query = entityManager.createQuery("SELECT d FROM Direccion d WHERE d.estado = TRUE");
			lista_direcciones = query.getResultList();
			Iterator<Direccion> iterator = lista_direcciones.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			return lista_direcciones;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Direccion consultarId(Short idDireccion) {
		try {
			direccion = entityManager.find(Direccion.class, idDireccion);
			return direccion;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void editar(Direccion direccion) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(direccion);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void eliminar(Short idDireccion) {
		try {
			direccion = entityManager.find(Direccion.class, idDireccion);
			entityManager.getTransaction().begin();
			entityManager.remove(direccion);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void pre_eliminar(Short idDireccion) {
		try {
			direccion = entityManager.find(Direccion.class, idDireccion);
			entityManager.getTransaction().begin();
			direccion.setEstado(false);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}
}
