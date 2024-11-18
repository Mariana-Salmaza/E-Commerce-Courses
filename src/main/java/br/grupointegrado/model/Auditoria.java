package br.grupointegrado.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @Column(name = "id_curso", nullable = false)
    private Integer idCurso;

    @Column(name = "dt", nullable = false)
    private LocalDate data;

    @Column(name = "valor_antigo")
    private BigDecimal valorAntigo;

    @Column(name = "valor_novo")
    private BigDecimal valorNovo;

    @Column(name = "mot", length = 255)
    private String motivo;

    public Auditoria() {}

    public Auditoria(Integer idCurso, LocalDate data, BigDecimal valorAntigo, BigDecimal valorNovo, String motivo) {
        this.idCurso = idCurso;
        this.data = data;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.motivo = motivo;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigDecimal getValorAntigo() {
        return valorAntigo;
    }

    public void setValorAntigo(BigDecimal valorAntigo) {
        this.valorAntigo = valorAntigo;
    }

    public BigDecimal getValorNovo() {
        return valorNovo;
    }

    public void setValorNovo(BigDecimal valorNovo) {
        this.valorNovo = valorNovo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "idAuditoria=" + idAuditoria +
                ", idCurso=" + idCurso +
                ", data=" + data +
                ", valorAntigo=" + valorAntigo +
                ", valorNovo=" + valorNovo +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}
