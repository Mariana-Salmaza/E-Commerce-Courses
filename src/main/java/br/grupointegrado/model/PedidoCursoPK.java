package br.grupointegrado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PedidoCursoPK implements Serializable {

    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "id_curso")
    private Integer idCurso;

    public PedidoCursoPK() {}

    public PedidoCursoPK(Integer idPedido, Integer idCurso) {
        this.idPedido = idPedido;
        this.idCurso = idCurso;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoCursoPK that = (PedidoCursoPK) o;
        return Objects.equals(idPedido, that.idPedido) &&
               Objects.equals(idCurso, that.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idCurso);
    }
}