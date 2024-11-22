package br.grupointegrado.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PIX") 
public class Pix extends FormaPagamento {

    private String chavePix;

    public Pix(String nomeForma, String descricao, String chavePix) {
        super(nomeForma, descricao);
        this.chavePix = chavePix;
    }

    public Pix() {
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public String toString() {
        return "Pix{" +
                "chavePix='" + chavePix + '\'' +
                "} " + super.toString();
    }
}
