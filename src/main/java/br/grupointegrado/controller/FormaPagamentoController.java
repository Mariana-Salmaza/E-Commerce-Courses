package br.grupointegrado.controller;

import br.grupointegrado.dto.FormaPagamentoRequestDTO;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private FormaPagamento converterParaEntidade(FormaPagamentoRequestDTO dto) {
        return new FormaPagamento(dto.nomeForma(), dto.descricao());
    }

    private FormaPagamentoRequestDTO converterParaDTO(FormaPagamento formaPagamento) {
        return new FormaPagamentoRequestDTO(formaPagamento.getIdForma(), formaPagamento.getNomeForma(), formaPagamento.getDescricao());
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoRequestDTO> criarFormaPagamento(@RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
        FormaPagamento formaPagamento = converterParaEntidade(formaPagamentoDTO);
        FormaPagamento novaFormaPagamento = formaPagamentoRepository.save(formaPagamento);
        FormaPagamentoRequestDTO novaFormaPagamentoDTO = converterParaDTO(novaFormaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFormaPagamentoDTO);
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoRequestDTO>> listarFormasPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        List<FormaPagamentoRequestDTO> formasPagamentoDTO = formasPagamento.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(formasPagamentoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoRequestDTO> buscarFormaPagamento(@PathVariable Integer id) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);
        if (formaPagamento == null) {
            return ResponseEntity.notFound().build();
        }
        FormaPagamentoRequestDTO formaPagamentoDTO = converterParaDTO(formaPagamento);
        return ResponseEntity.ok(formaPagamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoRequestDTO> atualizarFormaPagamento(@PathVariable Integer id, @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);
        if (formaPagamento == null) {
            return ResponseEntity.notFound().build();
        }

        formaPagamento.setNomeForma(formaPagamentoDTO.nomeForma());
        formaPagamento.setDescricao(formaPagamentoDTO.descricao());
        FormaPagamento formaPagamentoAtualizada = formaPagamentoRepository.save(formaPagamento);

        FormaPagamentoRequestDTO formaPagamentoAtualizadaDTO = converterParaDTO(formaPagamentoAtualizada);
        return ResponseEntity.ok(formaPagamentoAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFormaPagamento(@PathVariable Integer id) {
        if (formaPagamentoRepository.existsById(id)) {
            formaPagamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}