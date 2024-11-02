package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @JoinColumn(name = "id_pedido")
    private Long idPedido;

    @Id
    @JoinColumn(name = "id_curso")
    private Long idCurso;

    private int quantidade;
    private double preco;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "idPedido=" + idPedido +
                ", idCurso=" + idCurso +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }
}
