package com.controlebens.controle;

import com.controlebens.modelo.Bem;
import com.controlebens.modelo.TipoBem;
import com.controlebens.servico.BemService;
import com.controlebens.servico.TipoBemService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("bemBean")
@SessionScoped
public class BemManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BemService bemService;
    
    @Inject
    private TipoBemService tipoBemService;

    private Bem bemAtual;
    private List<Bem> bens;
    private List<TipoBem> tiposDeBem;

    @PostConstruct
    public void init() {
        this.bemAtual = new Bem();
        // carregarBens();
        // carregarTiposDeBem();
    }
    
    private Bem bemSelecionado;

    public Bem getBemSelecionado() {
        return bemSelecionado;
    }

    public void setBemSelecionado(Bem bemSelecionado) {
        this.bemSelecionado = bemSelecionado;
    }

    public String verDetalhes(Bem bem) {
        this.bemSelecionado = bem;
        return "bem_detalhes?faces-redirect=true"; 
    }

    public void carregarBens() {
        this.bens = bemService.listarTodos();
    }
    
    public void carregarTiposDeBem() {
        this.tiposDeBem = tipoBemService.listarTodos();
    }

    public void salvar() {
        try {
            bemService.salvar(bemAtual);
            bemAtual = new Bem();
            carregarBens();
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Bem salvo com sucesso."));
        } catch (IllegalArgumentException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de Validação", e.getMessage()));
        }
    }

    public void prepararEdicao(Bem bem) {
        this.bemAtual = bem;
        
        if (tiposDeBem == null || tiposDeBem.isEmpty()) {
            carregarTiposDeBem();
        }
        
        if (bemAtual.getTipoBem() != null && tiposDeBem != null) {
            for (TipoBem tipo : tiposDeBem) {
                if (tipo.getId().equals(bemAtual.getTipoBem().getId())) {
                    bemAtual.setTipoBem(tipo);  
                    break;
                }
            }
        }
    }

    public void remover(Bem bem) {
        bemService.remover(bem);
        carregarBens();
    }
    
    public void resetarFormulario() {
        this.bemAtual = new Bem();
    }
    
    public void handleTipoBemChange() {
        if (bemAtual.getTipoBem() != null && !bemAtual.getTipoBem().getAplicavel()) {
            bemAtual.setValorResidual(null);
        }
    }

    // Getters e Setters
    public Bem getBemAtual() {
        return bemAtual;
    }

    public void setBemAtual(Bem bemAtual) {
        this.bemAtual = bemAtual;
    }

    public List<Bem> getBens() {
        if (bens == null || bens.isEmpty()) {
            carregarBens();
        }
        return bens;
    }
    
    public void setBens(List<Bem> bens) {
        this.bens = bens;
    }
    
    public void atualizarTiposDeBem() {
        this.tiposDeBem = tipoBemService.listarTodos();
    }


    public List<TipoBem> getTiposDeBem() {
        if (tiposDeBem == null || tiposDeBem.isEmpty()) {
            carregarTiposDeBem();
        }
        return tiposDeBem;
    }

}
