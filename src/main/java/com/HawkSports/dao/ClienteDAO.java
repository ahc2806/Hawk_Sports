package com.HawkSports.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Cliente;

public class ClienteDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Cliente cliente = new Cliente();

	public void agregar(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> consultar() {
		try {
			List<Cliente> lista_clientes;
			Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.estado = TRUE");
			lista_clientes = query.getResultList();
			return lista_clientes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> consultarPapelera() {
		try {
			List<Cliente> lista_clientes;
			Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.estado = FALSE");
			lista_clientes = query.getResultList();
			return lista_clientes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Cliente consultarId(Short idCliente) {
		try {
			cliente = entityManager.find(Cliente.class, idCliente);
			return cliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editar(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminar(Short idCliente) {
		try {
			cliente = entityManager.find(Cliente.class, idCliente);
			entityManager.getTransaction().begin();
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}