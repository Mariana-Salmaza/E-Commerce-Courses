package br.grupointegrado.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra;

    private String status;

    public Compra() {
        this.dataCompra = LocalDateTime.now();
        this.status = "completa";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", cursoId=" + cursoId +
                ", dataCompra=" + dataCompra +
                ", status='" + status + '\'' +
                '}';
    }
}
