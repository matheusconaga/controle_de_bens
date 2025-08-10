package com.controlebens.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Entidade JPA para a tabela 'bens'.
 * Representa um bem com suas características e lógica de depreciação.
 */
@Entity
@Table(name = "bens")
public class Bem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bem")
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoBem tipoBem;

    @Column(name = "preco_compra", nullable = false)
    private BigDecimal precoCompra;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column(name = "vida_estimada_anos", nullable = false)
    private Integer vidaEstimadaAnos;

    @Column(name = "valor_residual")
    private BigDecimal valorResidual;

    public Bem() {
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

    public TipoBem getTipoBem() {
        return tipoBem;
    }

    public void setTipoBem(TipoBem tipoBem) {
        this.tipoBem = tipoBem;
        if (tipoBem != null && !tipoBem.getAplicavel()) {
            this.valorResidual = null;
        }
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getVidaEstimadaAnos() {
        return vidaEstimadaAnos;
    }

    public void setVidaEstimadaAnos(Integer vidaEstimadaAnos) {
        this.vidaEstimadaAnos = vidaEstimadaAnos;
    }

    public BigDecimal getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(BigDecimal valorResidual) {
        this.valorResidual = valorResidual;
    }

    public BigDecimal calcularDepreciacaoAnual() {
        if (this.precoCompra == null || this.vidaEstimadaAnos == null || this.vidaEstimadaAnos == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal valorResidual = this.valorResidual != null ? this.valorResidual : BigDecimal.ZERO;
        BigDecimal precoLiquido = this.precoCompra.subtract(valorResidual);
        return precoLiquido.divide(new BigDecimal(this.vidaEstimadaAnos), 2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Retorna a dataCompra como java.util.Date para compatibilidade com JSF converter.
     */
    public Date getDataCompraAsDate() {
        if (this.dataCompra == null) {
            return null;
        }
        return Date.from(this.dataCompra.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
