package com.controlebens.dao;

import com.controlebens.modelo.TipoBem;
import java.util.List;

public interface TipoBemDAO {
    void salvar(TipoBem tipoBem);
    void remover(TipoBem tipoBem);
    TipoBem buscarPorId(Integer id);
    List<TipoBem> listarTodos();
}