package br.grupointegrado.controller;

import br.grupointegrado.dto.FormaPagamentoRequestDTO;
import br.grupointegrado.model.CartaoCredito;
import br.grupointegrado.model.FormaPagamento;
import br.grupointegrado.model.Pix;
import br.grupointegrado.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private FormaPagamento converterParaEntidade(FormaPagamentoRequestDTO dto) {
        
        if ("PIX".equalsIgnoreCase(dto.tipoPagamento())) {
            
            return new Pix(dto.nomeForma(), dto.descricao());
        } else if ("CARTAO_CREDITO".equalsIgnoreCase(dto.tipoPagamento())) {
           
            return new CartaoCredito(dto.nomeForma(), dto.descricao());
        } else {
            
            throw new IllegalArgumentException("Tipo de pagamento inválido: " + dto.tipoPagamento());
        }
    }
    
    @PostMapping
    public ResponseEntity<FormaPagamento> criarFormaPagamento(@RequestBody FormaPagamentoRequestDTO formaPagamentoDTO) {
        try {
        
            FormaPagamento formaPagamento = converterParaEntidade(formaPagamentoDTO);
        
            FormaPagamento novaFormaPagamento = formaPagamentoRepository.save(formaPagamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaFormaPagamento);
        } catch (IllegalArgumentException e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarFormasPagamento() {
        List<FormaPagamento> formasPagamento = formaPagamentoRepository.findAll();
        return ResponseEntity.ok(formasPagamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarFormaPagamento(@PathVariable Integer id) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id).orElse(null);
        if (formaPagamento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(formaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizarFormaPagamento(
            @PathVariable Integer id,
            @RequestBody FormaPagamentoRequestDTO formaPagamentoDTO
    ) {
        FormaPagamento formaPagamentoExistente = formaPagamentoRepository.findById(id).orElse(null);
        if (formaPagamentoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        FormaPagamento formaPagamentoAtualizada = converterParaEntidade(formaPagamentoDTO);
        formaPagamentoAtualizada.setIdForma(formaPagamentoExistente.getIdForma());

        formaPagamentoRepository.save(formaPagamentoAtualizada);
        return ResponseEntity.ok(formaPagamentoAtualizada);
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
