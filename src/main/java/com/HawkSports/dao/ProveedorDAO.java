package com.HawkSports.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Proveedor;

public class ProveedorDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Proveedor proveedor = new Proveedor();

	public void guardar(Proveedor proveedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(proveedor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Proveedor> consultar() {
		try {
			List<Proveedor> lista_proveedores;
			Query query = entityManager.createQuery("SELECT p FROM Proveedor p WHERE p.estado = TRUE");
			lista_proveedores = query.getResultList();
			Iterator<Proveedor> iterator = lista_proveedores.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			return lista_proveedores;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Proveedor consultarId(Short idProveedor) {
		try {
			proveedor = entityManager.find(Proveedor.class, idProveedor);
			return proveedor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void editar(Proveedor proveedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(proveedor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void eliminar(Short idProveedor) {
		try {
			proveedor = entityManager.find(Proveedor.class, idProveedor);
			entityManager.getTransaction().begin();
			entityManager.remove(proveedor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void pre_eliminar(Short idProveedor) {
		try {
			proveedor = entityManager.find(Proveedor.class, idProveedor);
			entityManager.getTransaction().begin();
			proveedor.setEstado(false);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}
}
