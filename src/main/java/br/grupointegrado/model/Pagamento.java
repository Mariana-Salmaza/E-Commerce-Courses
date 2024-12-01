package br.grupointegrado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"pedido", "formaPagamento"})
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pag")
    private Integer idPagamento;

    @ManyToOne(optional = false) // Indica que 'pedido' não pode ser nulo
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private Pedido pedido;

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
    private List<FormaPagamento> formaPagamento;

    public Pagamento() {}

    public Pagamento(BigDecimal valor, List<FormaPagamento> formaPagamento, String status, Date dataPagamento) {
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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

    public List<FormaPagamento> getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(List<FormaPagamento> formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento [idPagamento=" + idPagamento + ", pedido=" + pedido + ", valor=" + valor
                + ", dataPagamento=" + dataPagamento + ", status=" + status + ", formaPagamento=" + formaPagamento
                + "]";
    }
}
