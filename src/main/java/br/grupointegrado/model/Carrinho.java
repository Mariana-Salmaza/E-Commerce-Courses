package br.grupointegrado.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "curso_id")
    private Long cursoId;

    @Column(name = "data_adicao")
    private LocalDateTime dataAdicao;

    public Carrinho() {
        this.dataAdicao = LocalDateTime.now();
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

    public LocalDateTime getDataAdicao() {
        return dataAdicao;
    }

    public void setDataAdicao(LocalDateTime dataAdicao) {
        this.dataAdicao = dataAdicao;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", cursoId=" + cursoId +
                ", dataAdicao=" + dataAdicao +
                '}';
    }
}
