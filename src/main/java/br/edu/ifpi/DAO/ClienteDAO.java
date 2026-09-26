package br.edu.ifpi.DAO;

import jakarta.persistence.*;
import br.edu.ifpi.Model.Cliente;
import br.edu.ifpi.JPAUtil;

public class ClienteDAO {

    public boolean salvar(Cliente cliente) {
        if (existePorCpf(cliente.getCpf())) return false;
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(cliente);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) { transaction.rollback(); }
            e.printStackTrace();
            return false;
        } finally { em.close(); }
    }

    public void atualizar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) { transaction.rollback(); }
            e.printStackTrace();
        } finally { em.close(); }
    }

    public boolean existePorCpf(String cpf) {
    EntityManager em = JPAUtil.getEntityManager();

    try {
        String jpql = "SELECT COUNT(c) FROM Cliente c WHERE c.cpf = :cpf";

        Long quantidade = em.createQuery(jpql, Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

        return quantidade > 0;
    } finally {
        em.close();
    }
}

    public Cliente buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.animais WHERE c.cpf = :cpf";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (NoResultException e) { return null; }
        finally { em.close(); }
    }

    public Cliente login(String cpf, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.animais WHERE c.cpf = :cpf AND c.senha = :senha";
            TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
            query.setParameter("cpf", cpf);
            query.setParameter("senha", senha);
            return query.getSingleResult();
        } catch (NoResultException e) { return null; }
        finally { em.close(); }
    }
}
