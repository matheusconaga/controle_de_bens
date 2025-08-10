package com.controlebens.controle;

import com.controlebens.modelo.TipoBem;
import com.controlebens.servico.TipoBemService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.PersistenceException;

@Named("tipoBemBean")
@SessionScoped
public class TipoBemManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TipoBemService tipoBemService;

    private TipoBem tipoBemAtual;
    private List<TipoBem> tiposDeBem;

    @PostConstruct
    public void init() {
        this.tipoBemAtual = new TipoBem();
        carregarTiposDeBem();
    }

    public void carregarTiposDeBem() {
        this.tiposDeBem = tipoBemService.listarTodos();
    }

    public void salvar() {
        try {
            tipoBemService.salvar(tipoBemAtual);
            
            tipoBemAtual = new TipoBem();
            carregarTiposDeBem();
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Tipo de bem salvo com sucesso."));
        } catch (PersistenceException e) {
            e.printStackTrace();
            
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Já existe um tipo de bem com este nome."));
        }
    }

    public void prepararEdicao(TipoBem tipoBem) {
        this.tipoBemAtual = tipoBem;
    }

    public void remover(TipoBem tipoBem) {
        tipoBemService.remover(tipoBem);
        carregarTiposDeBem();
    }

    // Getters e Setters
    public TipoBem getTipoBemAtual() {
        return tipoBemAtual;
    }

    public void setTipoBemAtual(TipoBem tipoBemAtual) {
        this.tipoBemAtual = tipoBemAtual;
    }

    public List<TipoBem> getTiposDeBem() {
        return tiposDeBem;
    }

    public void setTiposDeBem(List<TipoBem> tiposDeBem) {
        this.tiposDeBem = tiposDeBem;
    }
}