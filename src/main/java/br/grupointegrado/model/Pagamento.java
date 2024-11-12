package br.grupointegrado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pag")
    private Integer idPagamento;

    @Column(name = "vl_pedido", nullable = false)
    private BigDecimal valor;

    @Column(name = "dt_pagamento")
    private Date dataPagamento;

    @Column(name = "status_pagamento", nullable = false, length = 50)
    private String status;

    @ManyToMany
    @JoinTable(
        name = "pag_forma",
        joinColumns = @JoinColumn(name = "id_pag"),
        inverseJoinColumns = @JoinColumn(name = "id_forma")
    )
    private FormaPagamento formaPagamento;

    public Pagamento() {}

    public Pagamento(BigDecimal valor, FormaPagamento formaPagamento, String status, Date dataPagamento) {
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.status = status;
        this.dataPagamento = dataPagamento;
    }

    public Integer getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Integer idPagamento) {
        this.idPagamento = idPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", status='" + status + '\'' +
                ", formaPagamento=" + formaPagamento +
                '}';
    }
}
