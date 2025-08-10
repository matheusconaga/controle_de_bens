package com.controlebens.dao;

import com.controlebens.modelo.Bem;
import java.util.List;

public interface BemDAO {
    void salvar(Bem bem);
    void remover(Bem bem);
    Bem buscarPorId(Integer id);
    List<Bem> listarTodos();
}