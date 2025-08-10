package com.controlebens.modelo;

import jakarta.persistence.*;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


/**
 * Entidade JPA para a tabela 'tipos_de_bem'.
 * Define o tipo de bem (ex: "Computador") e se é aplicável para cálculo de valor residual.
 */
@Entity
@Table(name = "tipos_bem")
public class TipoBem implements Serializable {
    // Adicionando o serialVersionUID para boas práticas de serialização
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Boolean aplicavel;

    public TipoBem() {
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAplicavel() {
        return aplicavel;
    }

    public void setAplicavel(Boolean aplicavel) {
        this.aplicavel = aplicavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoBem)) return false;
        TipoBem other = (TipoBem) o;
        if (id == null || other.id == null) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TipoBem{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", aplicavel=" + aplicavel +
               '}';
    }
}
