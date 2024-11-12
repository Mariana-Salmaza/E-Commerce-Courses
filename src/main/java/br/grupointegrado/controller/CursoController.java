package br.grupointegrado.controller;

import br.grupointegrado.dto.CursoRequestDTO;
import br.grupointegrado.model.Curso;
import br.grupointegrado.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = this.repository.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public Curso findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não foi encontrado"));
    }

    @PostMapping
    public ResponseEntity<Curso> save(@RequestBody CursoRequestDTO dto) {
        if (dto.nomeCurso() == null || dto.nomeCurso().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Curso curso = new Curso();
        curso.setNomeCurso(dto.nomeCurso());
        curso.setValorCurso(dto.valorCurso());

        this.repository.save(curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não foi encontrado"));

        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody CursoRequestDTO dto) {
        if (dto.nomeCurso() == null || dto.nomeCurso().isEmpty()) {
            return ResponseEntity.status(428).build();
        }

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não foi encontrado"));

        curso.setNomeCurso(dto.nomeCurso());
        curso.setValorCurso(dto.valorCurso());

        this.repository.save(curso);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("/{id}/valor")
    public ResponseEntity<Curso> updateValorCurso(@PathVariable Integer id,
            @RequestBody BigDecimal valorCurso) {

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não foi encontrado"));

        curso.setValorCurso(valorCurso);

        this.repository.save(curso);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("/{id}/descricao")
    public ResponseEntity<Curso> updateDescricaoCurso(@PathVariable Integer id,
            @RequestBody String descricao) {

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não foi encontrado"));

                curso.setDescricao(descricao); 

        this.repository.save(curso);
        return ResponseEntity.ok(curso);
    }
}
