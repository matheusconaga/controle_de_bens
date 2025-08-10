package com.controlebens.servico;

import com.controlebens.dao.TipoBemDAO;
import com.controlebens.modelo.TipoBem;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
public class TipoBemService {

    @Inject
    private TipoBemDAO tipoBemDAO;

    @Transactional
    public void salvar(TipoBem tipoBem) {
        if (tipoBem.getNome() == null || tipoBem.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do tipo de bem não pode ser vazio.");
        }
        tipoBemDAO.salvar(tipoBem);
    }

    @Transactional
    public void remover(TipoBem tipoBem) {
        tipoBemDAO.remover(tipoBem);
    }

    public List<TipoBem> listarTodos() {
        return tipoBemDAO.listarTodos();
    }
    
    public TipoBem buscarPorId(Integer id) {
        return tipoBemDAO.buscarPorId(id);
    }
}