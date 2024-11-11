package br.grupointegrado.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dt", nullable = false)
    private Date data;

    @Column(name = "tabela_afetada", nullable = false, length = 50)
    private String tabelaAfetada;

    @Column(name = "acao", nullable = false, length = 50)
    private String acao;

    @Column(name = "mot", length = 255)
    private String motivo;

    @Column(name = "dados_anteriores", columnDefinition = "TEXT")
    private String dadosAnteriores;

    @Column(name = "dados_novos", columnDefinition = "TEXT")
    private String dadosNovos;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTabelaAfetada() {
        return tabelaAfetada;
    }

    public void setTabelaAfetada(String tabelaAfetada) {
        this.tabelaAfetada = tabelaAfetada;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDadosAnteriores() {
        return dadosAnteriores;
    }

    public void setDadosAnteriores(String dadosAnteriores) {
        this.dadosAnteriores = dadosAnteriores;
    }

    public String getDadosNovos() {
        return dadosNovos;
    }

    public void setDadosNovos(String dadosNovos) {
        this.dadosNovos = dadosNovos;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "id=" + id +
                ", data=" + data +
                ", tabelaAfetada='" + tabelaAfetada + '\'' +
                ", acao='" + acao + '\'' +
                ", motivo='" + motivo + '\'' +
                ", dadosAnteriores='" + dadosAnteriores + '\'' +
                ", dadosNovos='" + dadosNovos + '\'' +
                '}';
    }
}
