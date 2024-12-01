package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)
public abstract class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idForma;
    
    private String nomeForma;
    private String descricao;

    // Ajuste aqui: Use o nome "tipo_pagamento" como nome da coluna
    @Column(name = "tipo_pagamento", insertable = false, updatable = false)
    private String tipoPagamento;

    public FormaPagamento(String nomeForma, String descricao, String tipoPagamento) {
        this.nomeForma = nomeForma;
        this.descricao = descricao;
        this.tipoPagamento = tipoPagamento;
    }

    // Getters e setters
    public Integer getIdForma() {
        return idForma;
    }

    public void setIdForma(Integer idForma) {
        this.idForma = idForma;
    }

    public String getNomeForma() {
        return nomeForma;
    }

    public void setNomeForma(String nomeForma) {
        this.nomeForma = nomeForma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public abstract String exibirDetalhes();
}
