package br.grupointegrado.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "status_pagamento", nullable = false)
    private String statusPagamento; // PAGO, PENDENTE, CANCELADO

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public Pagamento() {
        this.dataPagamento = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", formaPagamento=" + formaPagamento +
                ", statusPagamento='" + statusPagamento + '\'' +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
