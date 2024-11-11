package br.grupointegrado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nm_curso", nullable = false, length = 100)
    private String nomeCurso;

    @Column(name = "vl_curso", nullable = false)
    private BigDecimal valorCurso;


    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public BigDecimal getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(BigDecimal valorCurso) {
        this.valorCurso = valorCurso;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", valorCurso=" + valorCurso +
                '}';
    }
}
