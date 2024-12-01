package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Crédito")
public class CartaoCredito extends FormaPagamento {

    public CartaoCredito() {
        super("Cartão de Crédito", "Pagamento via cartão de crédito", "Crédito"); 
    }

    public CartaoCredito(String nomeForma, String descricao) {
        super("Cartão de Crédito", "Pagamento via cartão de crédito", "Crédito");
    }

    @Override
    public String exibirDetalhes() {
        return "Pagamento via Cartão de Crédito";
    }
}
