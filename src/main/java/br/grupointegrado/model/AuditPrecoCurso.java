package br.grupointegrado.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_preco_curso")
public class AuditPrecoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    private double precoAnterior;
    private double precoNovo;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public AuditPrecoCurso() {
        this.dataAlteracao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double getPrecoAnterior() {
        return precoAnterior;
    }

    public void setPrecoAnterior(double precoAnterior) {
        this.precoAnterior = precoAnterior;
    }

    public double getPrecoNovo() {
        return precoNovo;
    }

    public void setPrecoNovo(double precoNovo) {
        this.precoNovo = precoNovo;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public String toString() {
        return "AuditPrecoCurso{" +
                "id=" + id +
                ", curso=" + curso +
                ", precoAnterior=" + precoAnterior +
                ", precoNovo=" + precoNovo +
                ", dataAlteracao=" + dataAlteracao +
                '}';
    }
}
