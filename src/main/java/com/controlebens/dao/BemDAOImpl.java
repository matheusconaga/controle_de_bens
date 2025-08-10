package com.controlebens.dao;

import com.controlebens.modelo.Bem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BemDAOImpl implements BemDAO {

    @PersistenceContext(unitName = "bensPU")
    private EntityManager em;

    @Override
    public void salvar(Bem bem) {
        if (bem.getId() == null) {
            em.persist(bem);
        } else {
            em.merge(bem);
        }
    }

    @Override
    public void remover(Bem bem) {
        em.remove(em.contains(bem) ? bem : em.merge(bem));
    }

    @Override
    public Bem buscarPorId(Integer id) {
        return em.find(Bem.class, id);
    }

    @Override
    public List<Bem> listarTodos() {
        return em.createQuery("SELECT b FROM Bem b", Bem.class).getResultList();
    }
}