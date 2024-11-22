package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "forma_pagamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Estratégia para uma única tabela
@DiscriminatorColumn(name = "tipo_pagamento", discriminatorType = DiscriminatorType.STRING)  // Coluna para diferenciar os tipos
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_forma")
    private Integer idForma;

    @Column(name = "nome_forma", nullable = false, length = 50)
    private String nomeForma;

    @Column(name = "descricao")
    private String descricao;

    public FormaPagamento(String nomeForma, String descricao) {
        this.nomeForma = nomeForma;
        this.descricao = descricao;
    }

    public FormaPagamento() {
    }

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

    @Override
    public String toString() {
        return "FormaPagamento{" +
                "idForma=" + idForma +
                ", nomeForma='" + nomeForma + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
