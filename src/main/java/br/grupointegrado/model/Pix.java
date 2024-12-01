package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Pix") 
public class Pix extends FormaPagamento {

    public Pix() {
        super("Pix", "Pagamento via Pix", "Pix"); 
    }

    public Pix(String nomeForma, String descricao) {
        super("Pix", "Pagamento via Pix", "Pix");
    }

    @Override
    public String exibirDetalhes() {
        return "Pagamento via Pix";
    }
}
