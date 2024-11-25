package br.grupointegrado.controller;

import br.grupointegrado.dto.PagamentoRequestDTO;
import br.grupointegrado.model.Pagamento;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.repository.PagamentoRepository;
import br.grupointegrado.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private Pagamento converterParaEntidade(PagamentoRequestDTO dto) {
       
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAllById(dto.idsFormasPagamento());
        return new Pagamento(
                dto.valorPedido(),
                formasPagamento,
                dto.statusPagamento(),
                dto.dataPagamento());
    }

    private PagamentoRequestDTO converterParaDTO(Pagamento pagamento) {
       
        List<Integer> idsFormasPagamento = pagamento.getFormaPagamento().stream()
                .map(FormaPagamento::getIdForma)
                .collect(Collectors.toList());

        return new PagamentoRequestDTO(
                pagamento.getIdPagamento(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getStatus(),
                idsFormasPagamento);
    }

    @PostMapping
    public ResponseEntity<PagamentoRequestDTO> criarPagamento(@RequestBody PagamentoRequestDTO pagamentoDTO) {
        Pagamento pagamento = converterParaEntidade(pagamentoDTO);
        Pagamento novoPagamento = pagamentoRepository.save(pagamento);
        PagamentoRequestDTO novoPagamentoDTO = converterParaDTO(novoPagamento);
        return ResponseEntity.ok(novoPagamentoDTO);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoRequestDTO>> listarPagamentos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        List<PagamentoRequestDTO> pagamentosDTO = pagamentos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pagamentosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoRequestDTO> buscarPagamento(@PathVariable Integer id) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }
        PagamentoRequestDTO pagamentoDTO = converterParaDTO(pagamento);
        return ResponseEntity.ok(pagamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoRequestDTO> atualizarPagamento(@PathVariable Integer id,
            @RequestBody PagamentoRequestDTO pagamentoDTO) {
        Pagamento pagamento = pagamentoRepository.findById(id).orElse(null);
        if (pagamento == null) {
            return ResponseEntity.notFound().build();
        }

        
        pagamento.setValor(pagamentoDTO.valorPedido());
        pagamento.setStatus(pagamentoDTO.statusPagamento());
        pagamento.setDataPagamento(pagamentoDTO.dataPagamento());

        
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAllById(pagamentoDTO.idsFormasPagamento());
        pagamento.setFormaPagamento(formasPagamento);

       
        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);
        PagamentoRequestDTO pagamentoAtualizadoDTO = converterParaDTO(pagamentoAtualizado);
        return ResponseEntity.ok(pagamentoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagamento(@PathVariable Integer id) {
        if (pagamentoRepository.existsById(id)) {
            pagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
