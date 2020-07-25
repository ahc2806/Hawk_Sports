package com.HawkSports.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		try {
			if(factory == null) {
				factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return factory;
	}
	
	public static void shutdown() {
		try {
			if(factory != null) {
				factory.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
