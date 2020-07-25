package com.HawkSports.dao;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.HawkSports.model.JPAUtil;
import com.HawkSports.model.Empleado;

public class EmpleadoDAO {
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	Empleado empleado = new Empleado();

	public void guardar(Empleado empleado) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(empleado);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Empleado> consultar() {
		try {
			List<Empleado> lista_empleados;
			Query query = entityManager.createQuery("SELECT e FROM Empleado e WHERE e.estado = TRUE");
			lista_empleados = query.getResultList();
			Iterator<Empleado> iterator = lista_empleados.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
			return lista_empleados;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Empleado consultarId(Short idEmpleado) {
		try {
			empleado = entityManager.find(Empleado.class, idEmpleado);
			return empleado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void editar(Empleado empleado) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(empleado);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void eliminar(Short idEmpleado) {
		try {
			empleado = entityManager.find(Empleado.class, idEmpleado);
			entityManager.getTransaction().begin();
			entityManager.remove(empleado);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}

	public void pre_eliminar(Short idEmpleado) {
		try {
			empleado = entityManager.find(Empleado.class, idEmpleado);
			entityManager.getTransaction().begin();
			empleado.setEstado(false);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPAUtil.shutdown();
		}
	}
}