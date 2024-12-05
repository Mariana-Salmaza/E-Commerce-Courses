package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pag_forma")
@IdClass(PagFormaId.class)
public class PagForma {

    @Id
    @Column(name = "id_pag")
    private Integer idPag;

    @Id
    @Column(name = "id_forma")
    private Integer idForma;

    @ManyToOne
    @JoinColumn(name = "id_pag", insertable = false, updatable = false)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "id_forma", insertable = false, updatable = false)
    private FormaPagamento formaPagamento;

    public Integer getIdPag() {
        return idPag;
    }

    public void setIdPag(Integer idPag) {
        this.idPag = idPag;
    }

    public Integer getIdForma() {
        return idForma;
    }

    public void setIdForma(Integer idForma) {
        this.idForma = idForma;
    }

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
}
