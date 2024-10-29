package br.grupointegrado.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "compra_id")
    private Long compraId;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    private String status;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    public Pagamento() {
        this.dataPagamento = LocalDateTime.now();
        this.status = "concluído";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", compraId=" + compraId +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", status='" + status + '\'' +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
