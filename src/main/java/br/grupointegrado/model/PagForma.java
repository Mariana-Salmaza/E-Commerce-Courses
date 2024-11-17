package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pag_forma")
public class PagForma {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pag")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "id_forma")
    private FormaPagamento formaPagamento;

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "PagForma [pagamento=" + pagamento + ", formaPagamento=" + formaPagamento + "]";
    }
}
