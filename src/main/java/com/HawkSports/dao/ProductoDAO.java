package com.HawkSports.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Producto;

@Named
@RequestScoped
public class ProductoDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

	public void agregar(Producto producto) {
		try {
			entityManager.getTransaction().begin(); // Se inicia una transacción
			entityManager.persist(producto); // Crea una instancia de producto y la guarda
			entityManager.getTransaction().commit(); // Finaliza la transacción
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Producto> consultar() {
		try {
			List<Producto> lista_productos = new ArrayList<>();
			Query query = entityManager.createQuery("SELECT p FROM Producto p WHERE p.estado = TRUE");
			lista_productos = query.getResultList();
			return lista_productos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Producto consultarId(Short idProducto) {
		try {
			Producto producto = new Producto();
			producto = entityManager.find(Producto.class, idProducto); // Busca por Id y devuelve la instancia
			return producto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editar(Producto producto) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(producto); // Actualiza un registro de producto
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminar(Short idProducto) {
		try {
			Producto producto = new Producto();
			producto = entityManager.find(Producto.class, idProducto);
			entityManager.getTransaction().begin();
			entityManager.remove(producto);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}