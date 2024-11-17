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

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    public Curso(String nomeCurso, BigDecimal valorCurso, String descricao) {
        this.nomeCurso = nomeCurso;
        this.valorCurso = valorCurso;
        this.descricao = descricao;
    }

    public Curso() {
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", nomeCurso='" + nomeCurso + '\'' +
                ", valorCurso=" + valorCurso +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
