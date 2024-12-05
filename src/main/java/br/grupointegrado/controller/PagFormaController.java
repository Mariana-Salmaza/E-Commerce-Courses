package br.grupointegrado.controller;

import br.grupointegrado.dto.PagFormaRequestDTO;
import br.grupointegrado.model.PagForma;
import br.grupointegrado.model.PagFormaId;
import br.grupointegrado.repository.PagFormaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pag-forma")
public class PagFormaController {

    @Autowired
    private PagFormaRepository pagFormaRepository;

    private PagFormaRequestDTO converterParaDTO(PagForma pagForma) {
        return new PagFormaRequestDTO(
                pagForma.getPagamento().getIdPagamento(),
                pagForma.getFormaPagamento().getIdForma());
    }

    private PagForma converterParaEntidade(PagFormaRequestDTO dto) {
        PagForma pagForma = new PagForma();
        pagForma.setIdPag(dto.idPag());
        pagForma.setIdForma(dto.idForma());
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

    @GetMapping("/{idPag}/{idForma}")
    public ResponseEntity<PagFormaRequestDTO> buscarPagForma(@PathVariable Integer idPag, @PathVariable Integer idForma) {
        PagFormaId id = new PagFormaId();
        id.setIdPag(idPag);
        id.setIdForma(idForma);

        Optional<PagForma> pagForma = pagFormaRepository.findById(id);
        if (pagForma.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PagFormaRequestDTO pagFormaDTO = converterParaDTO(pagForma.get());
        return ResponseEntity.ok(pagFormaDTO);
    }

    @PutMapping("/{idPag}/{idForma}")
    public ResponseEntity<PagFormaRequestDTO> atualizarPagForma(@PathVariable Integer idPag,
            @PathVariable Integer idForma,
            @RequestBody PagFormaRequestDTO pagFormaDTO) {
        PagFormaId id = new PagFormaId();
        id.setIdPag(idPag);
        id.setIdForma(idForma);

        Optional<PagForma> pagFormaOptional = pagFormaRepository.findById(id);
        if (pagFormaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PagForma pagForma = pagFormaOptional.get();
        pagForma.setIdPag(pagFormaDTO.idPag());
        pagForma.setIdForma(pagFormaDTO.idForma());

        PagForma pagFormaAtualizado = pagFormaRepository.save(pagForma);
        PagFormaRequestDTO pagFormaAtualizadoDTO = converterParaDTO(pagFormaAtualizado);
        return ResponseEntity.ok(pagFormaAtualizadoDTO);
    }

    @DeleteMapping("/{idPag}/{idForma}")
    public ResponseEntity<Void> deletarPagForma(@PathVariable Integer idPag, @PathVariable Integer idForma) {
        PagFormaId id = new PagFormaId();
        id.setIdPag(idPag);
        id.setIdForma(idForma);

        if (pagFormaRepository.existsById(id)) {
            pagFormaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
