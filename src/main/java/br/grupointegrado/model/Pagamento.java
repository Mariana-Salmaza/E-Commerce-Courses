package br.grupointegrado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pag")
    private Integer idPag;

    @Column(name = "vl_pedido", nullable = false)
    private BigDecimal vlPedido;

    @Column(name = "dt_pagamento")
    private Date dtPagamento;

    @Column(name = "status_pagamento", nullable = false, length = 20)
    private String statusPagamento;

    @ManyToMany
    @JoinTable(
        name = "pag_forma",
        joinColumns = @JoinColumn(name = "id_pag"),
        inverseJoinColumns = @JoinColumn(name = "id_forma")
    )
    private List<FormaPagamento> formasPagamento;


    public Integer getIdPag() {
        return idPag;
    }

    public void setIdPag(Integer idPag) {
        this.idPag = idPag;
    }

    public BigDecimal getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(BigDecimal vlPedido) {
        this.vlPedido = vlPedido;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPag=" + idPag +
                ", vlPedido=" + vlPedido +
                ", dtPagamento=" + dtPagamento +
                ", statusPagamento='" + statusPagamento + '\'' +
                '}';
    }
}
