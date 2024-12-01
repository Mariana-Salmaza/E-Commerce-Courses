package br.grupointegrado.controller;

import br.grupointegrado.dto.PedidoRequestDTO;
import br.grupointegrado.model.Pedido;
import br.grupointegrado.model.User;
import br.grupointegrado.model.Curso;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.repository.PedidoRepository;
import br.grupointegrado.repository.UserRepository;
import br.grupointegrado.repository.CursoRepository;  
import br.grupointegrado.repository.FormaPagamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;


    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Integer id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody PedidoRequestDTO dto) {
        User user = userRepository.findById(dto.idUser()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(dto.idForma()).orElseThrow(() -> new IllegalArgumentException("Forma de pagamento não encontrada"));
        Curso curso = cursoRepository.findById(dto.idCurso())
            .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUser(user);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setCurso(curso);
        pedido.setPago(dto.pago());
        pedido.setDataPedido(dto.dataPedido());

        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(@PathVariable Integer id, @RequestBody PedidoRequestDTO dto) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    User user = userRepository.findById(dto.idUser()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
                    FormaPagamento formaPagamento = formaPagamentoRepository.findById(dto.idForma()).orElseThrow(() -> new IllegalArgumentException("Forma de pagamento não encontrada"));
                    Curso curso = cursoRepository.findById(dto.idCurso())
                        .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

                    pedido.setUser(user);
                    pedido.setFormaPagamento(formaPagamento);
                    pedido.setCurso(curso);
                    pedido.setPago(dto.pago());
                    pedido.setDataPedido(dto.dataPedido());

                    return ResponseEntity.ok(pedidoRepository.save(pedido));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedidoRepository.delete(pedido);
        return ResponseEntity.noContent().build();
    }   

    @PostMapping("/{id}/pago")
    public ResponseEntity<Pedido> atualizarStatusPago(@PathVariable Integer id, @RequestBody Boolean pago) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setPago(pago);
                    return ResponseEntity.ok(pedidoRepository.save(pedido));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
