package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_curso")
public class PedidoCurso {

    @EmbeddedId
    private PedidoCursoPK id;

    @ManyToOne
    @MapsId("idPedido") 
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @MapsId("idCurso")
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    private Curso curso;

    @Column(name = "quantidade")
    private Integer quantidade;


    public PedidoCursoPK getId() {
        return id;
    }

    public void setId(PedidoCursoPK id) {
        if (id == null || id.getIdPedido() == null || id.getIdCurso() == null) {
            throw new IllegalArgumentException("A chave composta não pode ser nula");
        }
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não pode ser nulo");
        }
        this.pedido = pedido;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("Curso não pode ser nulo");
        }
        this.curso = curso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if (quantidade == null) {
            throw new IllegalArgumentException("Quantidade não pode ser nula");
        }
        this.quantidade = quantidade;
    }
}
