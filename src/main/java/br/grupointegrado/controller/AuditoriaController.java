package br.grupointegrado.controller;

import br.grupointegrado.dto.AuditoriaRequestDTO;
import br.grupointegrado.model.Auditoria;
import br.grupointegrado.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auditorias")
public class AuditoriaController {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @PostMapping
    public ResponseEntity<AuditoriaRequestDTO> registrarAuditoria(@RequestBody AuditoriaRequestDTO auditoriaDTO) {
        Auditoria auditoria = new Auditoria();
        auditoria.setIdCurso(auditoriaDTO.idCurso());
        auditoria.setData(LocalDate.now());
        auditoria.setValorAntigo(auditoriaDTO.valorAntigo());
        auditoria.setValorNovo(auditoriaDTO.valorNovo());
        auditoria.setMotivo(auditoriaDTO.motivo());

        auditoriaRepository.save(auditoria);

        return ResponseEntity.ok(auditoriaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaRequestDTO>> listarAuditorias() {
        List<Auditoria> auditorias = auditoriaRepository.findAll();
        List<AuditoriaRequestDTO> auditoriasDTO = auditorias.stream()
                .map(auditoria -> new AuditoriaRequestDTO(
                        auditoria.getIdAuditoria(),
                        auditoria.getIdCurso(),
                        auditoria.getData(),
                        auditoria.getValorAntigo(),
                        auditoria.getValorNovo(),
                        auditoria.getMotivo()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(auditoriasDTO);
    }
}
