package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PIX")
public class Pix extends FormaPagamento {

    @Column(name = "chave_pix")
    private String chavePix;

    public Pix() {
    }

    public Pix(String nomeForma, String descricao, String chavePix) {
        super(nomeForma, descricao);
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public String exibirDetalhes() {
        return "Pagamento via Pix: " + getNomeForma() + " | Chave Pix: " + chavePix;
    }
}
