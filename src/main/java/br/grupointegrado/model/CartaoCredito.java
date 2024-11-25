package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("CARTAO_CREDITO")
public class CartaoCredito extends FormaPagamento {

    @Column(name = "numero_cartao")
    private String numeroCartao;

    @Column(name = "nome_titular")
    private String nomeTitular;

    public CartaoCredito() {
    }

    public CartaoCredito(String nomeForma, String descricao, String numeroCartao, String nomeTitular) {
        super(nomeForma, descricao);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    @Override
    public String exibirDetalhes() {
        return "Pagamento via Cartão de Crédito: " + getNomeForma() + " | Nome do Titular: " + nomeTitular;
    }
}
