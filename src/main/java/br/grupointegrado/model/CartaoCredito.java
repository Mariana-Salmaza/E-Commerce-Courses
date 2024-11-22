package br.grupointegrado.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARTAO_CREDITO")
public class CartaoCredito extends FormaPagamento {

    private String numeroCartao;
    private String nomeTitular;

    public CartaoCredito(String nomeForma, String descricao, String numeroCartao, String nomeTitular) {
        super(nomeForma, descricao);
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
    }

    public CartaoCredito() {
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
    public String toString() {
        return "CartaoCredito{" +
                "numeroCartao='" + numeroCartao + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                "} " + super.toString();
    }
}
