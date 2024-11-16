package br.grupointegrado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido_curso")
public class PedidoCurso {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @Column(name = "quantidade")
    private Integer quantidade;

    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "PedidoCurso{" +
                "curso=" + curso +
                ", pedido=" + pedido +
                ", quantidade=" + quantidade +
                '}';
    }
}
