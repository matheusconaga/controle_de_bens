package com.controlebens.dao;

import com.controlebens.modelo.TipoBem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless 
public class TipoBemDAOImpl implements TipoBemDAO {

    @PersistenceContext(unitName = "bensPU")
    private EntityManager em;

    @Override
    public void salvar(TipoBem tipoBem) {
        if (tipoBem.getId() == null) {
            em.persist(tipoBem);
        } else {
            em.merge(tipoBem);
        }
    }

    @Override
    public void remover(TipoBem tipoBem) {
        TipoBem gerenciado = em.find(TipoBem.class, tipoBem.getId());
        if (gerenciado != null) {
            em.remove(gerenciado);
        }
    }


    @Override
    public TipoBem buscarPorId(Integer id) {
        return em.find(TipoBem.class, id);
    }

    @Override
    public List<TipoBem> listarTodos() {
        return em.createQuery("SELECT t FROM TipoBem t", TipoBem.class).getResultList();
    }
}