package br.grupointegrado.model;

import java.io.Serializable;
import java.util.Objects;

public class PagFormaId implements Serializable {
    private Integer idPag;
    private Integer idForma;

    public Integer getIdPag() {
        return idPag;
    }

    public void setIdPag(Integer idPag) {
        this.idPag = idPag;
    }

    public Integer getIdForma() {
        return idForma;
    }

    public void setIdForma(Integer idForma) {
        this.idForma = idForma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagFormaId that = (PagFormaId) o;
        return Objects.equals(idPag, that.idPag) && Objects.equals(idForma, that.idForma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPag, idForma);
    }
}
