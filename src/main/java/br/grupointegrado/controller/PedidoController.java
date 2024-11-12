package br.grupointegrado.controller;

import br.grupointegrado.dto.PedidoRequestDTO;
import br.grupointegrado.model.Pedido;
import br.grupointegrado.model.User;
import br.grupointegrado.model.Pagamento;
import br.grupointegrado.repository.PedidoRepository;
import br.grupointegrado.repository.UserRepository;
import br.grupointegrado.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    
    private PedidoRequestDTO converterParaDTO(Pedido pedido) {
        return new PedidoRequestDTO(
                pedido.getIdPedido(),
                pedido.getUser().getIdUser(),
                pedido.getPagamento().getIdPagamento(),
                pedido.getPago(),
                pedido.getDataPedido()
        );
    }

    private Pedido converterParaEntidade(PedidoRequestDTO dto) {
        User user = userRepository.findById(dto.idUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Pagamento pagamento = pagamentoRepository.findById(dto.idPag())
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUser(user);
        pedido.setPagamento(pagamento);
        pedido.setPago(dto.pago());
        pedido.setDataPedido(dto.dataPedido());
        return pedido;
    }

    @PostMapping
    public ResponseEntity<PedidoRequestDTO> criarPedido(@RequestBody PedidoRequestDTO pedidoDTO) {
        Pedido pedido = converterParaEntidade(pedidoDTO);
        Pedido novoPedido = pedidoRepository.save(pedido);
        PedidoRequestDTO novoPedidoDTO = converterParaDTO(novoPedido);
        return ResponseEntity.ok(novoPedidoDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoRequestDTO>> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoRequestDTO> pedidosDTO = pedidos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoRequestDTO> buscarPedido(@PathVariable Integer id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        PedidoRequestDTO pedidoDTO = converterParaDTO(pedido);
        return ResponseEntity.ok(pedidoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoRequestDTO> atualizarPedido(@PathVariable Integer id, @RequestBody PedidoRequestDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        User user = userRepository.findById(pedidoDTO.idUser())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        Pagamento pagamento = pagamentoRepository.findById(pedidoDTO.idPag())
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));

        pedido.setUser(user);
        pedido.setPagamento(pagamento);
        pedido.setPago(pedidoDTO.pago());
        pedido.setDataPedido(pedidoDTO.dataPedido());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        PedidoRequestDTO pedidoAtualizadoDTO = converterParaDTO(pedidoAtualizado);
        return ResponseEntity.ok(pedidoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
