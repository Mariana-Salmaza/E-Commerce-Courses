package br.grupointegrado.controller;

import br.grupointegrado.dto.AuditoriaRequestDTO;
import br.grupointegrado.model.Auditoria;
import br.grupointegrado.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        auditoria.setTabelaAfetada(auditoriaDTO.tabelaAfetada());
        auditoria.setAcao("REGISTRO"); 
        auditoria.setMotivo("Motivo do registro");
        auditoria.setData(new Date());
        auditoria.setDadosAnteriores(auditoriaDTO.dadosAnteriores());
        auditoria.setDadosNovos(auditoriaDTO.dadosNovos());
        auditoriaRepository.save(auditoria);
        
        return ResponseEntity.ok(auditoriaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaRequestDTO>> listarAuditorias() {
        List<Auditoria> auditorias = auditoriaRepository.findAll();
        List<AuditoriaRequestDTO> auditoriasDTO = auditorias.stream()
            .map(auditoria -> new AuditoriaRequestDTO(
                auditoria.getId(),
                auditoria.getData(),
                auditoria.getTabelaAfetada(),
                auditoria.getAcao(),
                auditoria.getMotivo(),
                auditoria.getDadosAnteriores(),
                auditoria.getDadosNovos()
            ))
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(auditoriasDTO);
    }
}
