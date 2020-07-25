package com.HawkSports.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Cliente;

public class ClienteDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Cliente cliente = new Cliente();

	public void guardar(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> consultar() {
		try {
			List<Cliente> lista_clientes;
			Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.estado = TRUE");
			lista_clientes = query.getResultList();
			Iterator<Cliente> iterator = lista_clientes.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
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
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void editar(Cliente cliente) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
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
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void pre_eliminar(Short idCliente) {
		try {
			Cliente cliente = entityManager.find(Cliente.class, idCliente);
			entityManager.getTransaction().begin();
			cliente.setEstado(false);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}
}
