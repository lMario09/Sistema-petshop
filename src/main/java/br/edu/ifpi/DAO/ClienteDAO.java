package br.edu.ifpi.DAO;

import jakarta.persistence.*;
import br.edu.ifpi.Model.Cliente;
import br.edu.ifpi.JPAUtil;

public class ClienteDAO {

    public void salvar(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(cliente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) { transaction.rollback(); }
            e.printStackTrace();
        } finally { em.close(); }
    }

    public Cliente buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Cliente.class, id); }
        finally { em.close(); }
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
