package com.HawkSports.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Proveedor;

public class ProveedorDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Proveedor proveedor = new Proveedor();

	public void agregar(Proveedor proveedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(proveedor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Proveedor> consultar() {
		try {
			List<Proveedor> lista_proveedores;
			Query query = entityManager.createQuery("SELECT p FROM Proveedor p WHERE p.estado = TRUE");
			lista_proveedores = query.getResultList();
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
		}
	}

	public void editar(Proveedor proveedor) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(proveedor);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
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
		}
	}
}
