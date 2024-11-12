package br.grupointegrado.controller;

import br.grupointegrado.dto.PagFormaRequestDTO;
import br.grupointegrado.model.PagForma;
import br.grupointegrado.model.Pagamento;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.repository.PagFormaRepository;
import br.grupointegrado.repository.PagamentoRepository;
import br.grupointegrado.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pag-forma")
public class PagFormaController {

    @Autowired
    private PagFormaRepository pagFormaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private PagFormaRequestDTO converterParaDTO(PagForma pagForma) {
        return new PagFormaRequestDTO(
                pagForma.getPagamento().getIdPagamento(),
                pagForma.getFormaPagamento().getIdForma()
        );
    }

    
    private PagForma converterParaEntidade(PagFormaRequestDTO dto) {
        Pagamento pagamento = pagamentoRepository.findById(dto.idPag())
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(dto.idForma())
                .orElseThrow(() -> new IllegalArgumentException("Forma de pagamento não encontrada"));

        PagForma pagForma = new PagForma();
        pagForma.setPagamento(pagamento);
        pagForma.setFormaPagamento(formaPagamento);
        return pagForma;
    }

    
    @PostMapping
    public ResponseEntity<PagFormaRequestDTO> criarPagForma(@RequestBody PagFormaRequestDTO pagFormaDTO) {
        PagForma pagForma = converterParaEntidade(pagFormaDTO);
        PagForma novoPagForma = pagFormaRepository.save(pagForma);
        PagFormaRequestDTO novoPagFormaDTO = converterParaDTO(novoPagForma);
        return ResponseEntity.ok(novoPagFormaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PagFormaRequestDTO>> listarPagFormas() {
        List<PagForma> pagFormas = pagFormaRepository.findAll();
        List<PagFormaRequestDTO> pagFormasDTO = pagFormas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pagFormasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagFormaRequestDTO> buscarPagForma(@PathVariable Integer id) {
        PagForma pagForma = pagFormaRepository.findById(id).orElse(null);
        if (pagForma == null) {
            return ResponseEntity.notFound().build();
        }
        PagFormaRequestDTO pagFormaDTO = converterParaDTO(pagForma);
        return ResponseEntity.ok(pagFormaDTO);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PagFormaRequestDTO> atualizarPagForma(@PathVariable Integer id, @RequestBody PagFormaRequestDTO pagFormaDTO) {
        PagForma pagForma = pagFormaRepository.findById(id).orElse(null);
        if (pagForma == null) {
            return ResponseEntity.notFound().build();
        }

        Pagamento pagamento = pagamentoRepository.findById(pagFormaDTO.idPag())
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado"));
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(pagFormaDTO.idForma())
                .orElseThrow(() -> new IllegalArgumentException("Forma de pagamento não encontrada"));

        pagForma.setPagamento(pagamento);
        pagForma.setFormaPagamento(formaPagamento);

        PagForma pagFormaAtualizado = pagFormaRepository.save(pagForma);
        PagFormaRequestDTO pagFormaAtualizadoDTO = converterParaDTO(pagFormaAtualizado);
        return ResponseEntity.ok(pagFormaAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPagForma(@PathVariable Integer id) {
        if (pagFormaRepository.existsById(id)) {
            pagFormaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
