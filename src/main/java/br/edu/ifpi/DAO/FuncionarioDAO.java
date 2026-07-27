package br.edu.ifpi.DAO;

import jakarta.persistence.*;
import br.edu.ifpi.Model.Funcionario;
import br.edu.ifpi.JPAUtil;

public class FuncionarioDAO {

    public void salvar(Funcionario funcionario) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(funcionario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) { transaction.rollback(); }
            e.printStackTrace();
        } finally { em.close(); }
    }

    public Funcionario buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try { return em.find(Funcionario.class, id); }
        finally { em.close(); }
    }

    public Funcionario buscarPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf";
            TypedQuery<Funcionario> query = em.createQuery(jpql, Funcionario.class);
            query.setParameter("cpf", cpf);
            return query.getSingleResult();
        } catch (NoResultException e) { return null; }
        finally { em.close(); }
    }
}
