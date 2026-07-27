package br.edu.ifpi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("petshop-banco");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
