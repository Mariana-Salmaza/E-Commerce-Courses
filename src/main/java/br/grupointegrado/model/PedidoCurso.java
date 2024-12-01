package br.grupointegrado.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PedidoCurso {

    @EmbeddedId
    private PedidoCursoPK id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    @JsonIgnoreProperties("pedidoCurso")
    @JsonBackReference  
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_curso", insertable = false, updatable = false)
    private Curso curso;

    @Column(name = "quantidade")
    private Integer quantidade;


    public PedidoCurso() {}

    public PedidoCurso(PedidoCursoPK id, Pedido pedido, Curso curso, Integer quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.curso = curso;
        this.quantidade = quantidade;
    }

    public PedidoCursoPK getId() {
        return id;
    }

    public void setId(PedidoCursoPK id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}