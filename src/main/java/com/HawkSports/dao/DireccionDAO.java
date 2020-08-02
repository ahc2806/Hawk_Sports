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
	
	public void agregar(Direccion direccion) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(direccion);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
	}

	public void editar(Direccion direccion) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(direccion);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
	}
	
	public Short ultimo_id() {
		Query query = entityManager.createQuery("SELECT MAX(d.idDireccion) FROM Direccion d");
		Short id = (Short) query.getSingleResult();
		return id;
	}
}
