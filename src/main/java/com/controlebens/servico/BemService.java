package com.controlebens.servico;

import com.controlebens.dao.BemDAO;
import com.controlebens.modelo.Bem;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

import java.util.List;


@Stateless
public class BemService {

    @Inject
    private BemDAO bemDAO;

    @Transactional
    public void salvar(@NotNull Bem bem) {
        if (bem.getTipoBem() != null && bem.getTipoBem().getAplicavel() && bem.getValorResidual() == null) {
            throw new IllegalArgumentException("O valor residual é obrigatório para este tipo de bem.");
        }
        if (bem.getTipoBem() != null && !bem.getTipoBem().getAplicavel()) {
            bem.setValorResidual(null);
        }

        bemDAO.salvar(bem);
    }

    @Transactional
    public void remover(@NotNull Bem bem) {
        bemDAO.remover(bem);
    }

    public List<Bem> listarTodos() {
        return bemDAO.listarTodos();
    }

    public Bem buscarPorId(Integer id) {
        return bemDAO.buscarPorId(id);
    }
}