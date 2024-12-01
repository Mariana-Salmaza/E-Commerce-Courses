package br.grupointegrado.controller;

import br.grupointegrado.dto.PagamentoRequestDTO;
import br.grupointegrado.model.Pagamento;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.model.Pedido;
import br.grupointegrado.repository.PagamentoRepository;
import br.grupointegrado.repository.FormaPagamentoRepository;
import br.grupointegrado.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Integer id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        return ResponseEntity.ok(pagamento);
    }

    @PostMapping
    public ResponseEntity<Pagamento> salvar(@RequestBody PagamentoRequestDTO dto) {
        if (dto.valorPedido() == null || dto.valorPedido().compareTo(BigDecimal.ZERO) <= 0) {
            return ResponseEntity.status(400).body(null);
        }

        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAllById(dto.idsFormasPagamento());
        if (formasPagamento.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma forma de pagamento encontrada");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(dto.valorPedido());
        pagamento.setStatus(dto.statusPagamento());
        pagamento.setDataPagamento(dto.dataPagamento());
        pagamento.setPedido(pedido);
        pagamento.setFormaPagamento(formasPagamento);

        pagamentoRepository.save(pagamento);
        return ResponseEntity.ok(pagamento);
    }
}
