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
        if (idPedido == null || idCurso == null) {
            throw new IllegalArgumentException("Os IDs não podem ser nulos");
        }
        this.idPedido = idPedido;
        this.idCurso = idCurso;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido, idCurso);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PedidoCursoPK that = (PedidoCursoPK) obj;
        return idPedido.equals(that.idPedido) && idCurso.equals(that.idCurso);
    }

    @Override
    public String toString() {
        return "PedidoCursoPK{" +
               "idPedido=" + idPedido +
               ", idCurso=" + idCurso +
               '}';
    }
}
