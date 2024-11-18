package br.grupointegrado.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_pag", referencedColumnName = "id_pag")
    private Pagamento pagamento;

    @Column(name = "pago")
    private Boolean pago;

    @Column(name = "data_pedido")
    private java.util.Date dataPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<PedidoCurso> pedidoCursos;

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public java.util.Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(java.util.Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<PedidoCurso> getPedidoCursos() {
        return pedidoCursos;
    }

    public void setPedidoCursos(List<PedidoCurso> pedidoCursos) {
        this.pedidoCursos = pedidoCursos;
    }
}
