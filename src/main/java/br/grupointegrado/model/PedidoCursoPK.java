package br.grupointegrado.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class PedidoCursoPK implements Serializable {

    private Integer idPedido;
    private Integer idCurso;

    public PedidoCursoPK(Integer idPedido, Integer idCurso) {
        this.idPedido = idPedido;
        this.idCurso = idCurso;
    }

    public PedidoCursoPK() {}

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
        return idPedido.equals(that.idPedido) && idCurso.equals(that.idCurso);
    }

    @Override
    public int hashCode() {
        return 31 * idPedido.hashCode() + idCurso.hashCode();
    }
}
