package br.grupointegrado.controller;

import br.grupointegrado.dto.PedidoCursoRequestDTO;
import br.grupointegrado.model.Curso;
import br.grupointegrado.model.Pedido;
import br.grupointegrado.model.PedidoCurso;
import br.grupointegrado.model.PedidoCursoPK;
import br.grupointegrado.repository.PedidoCursoRepository;
import br.grupointegrado.repository.PedidoRepository;
import br.grupointegrado.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido-cursos")
public class PedidoCursoController {

    @Autowired
    private PedidoCursoRepository pedidoCursoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<PedidoCurso> criarPedidoCurso(@RequestBody PedidoCursoRequestDTO pedidoCursoDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoCursoDTO.idPedido()).orElseThrow();
        Curso curso = cursoRepository.findById(pedidoCursoDTO.idCurso()).orElseThrow();
        
        PedidoCursoPK pedidoCursoPK = new PedidoCursoPK(pedidoCursoDTO.idPedido(), pedidoCursoDTO.idCurso());
        
        PedidoCurso pedidoCurso = new PedidoCurso();
        pedidoCurso.setId(pedidoCursoPK);
        pedidoCurso.setPedido(pedido);
        pedidoCurso.setCurso(curso);
        pedidoCurso.setQuantidade(pedidoCursoDTO.quantidade());
        
        PedidoCurso novoPedidoCurso = pedidoCursoRepository.save(pedidoCurso);
        return ResponseEntity.ok(novoPedidoCurso);
    }
    

    @GetMapping
    public ResponseEntity<List<PedidoCurso>> listarPedidoCursos() {
        List<PedidoCurso> pedidoCursos = pedidoCursoRepository.findAll();
        return ResponseEntity.ok(pedidoCursos);
    }

    @GetMapping("/{idPedido}/{idCurso}")
    public ResponseEntity<PedidoCurso> buscarPedidoCurso(@PathVariable Integer idPedido, @PathVariable Integer idCurso) {
        Optional<PedidoCurso> pedidoCurso = pedidoCursoRepository.findById(new PedidoCursoPK(idPedido, idCurso));
        return pedidoCurso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{idPedido}/{idCurso}")
    public ResponseEntity<PedidoCurso> atualizarPedidoCurso(@PathVariable Integer idPedido, @PathVariable Integer idCurso, @RequestBody PedidoCurso pedidoCurso) {
        Optional<PedidoCurso> pedidoCursoOptional = pedidoCursoRepository.findById(new PedidoCursoPK(idPedido, idCurso));
        
        if (pedidoCursoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        PedidoCurso pedidoCursoExistente = pedidoCursoOptional.get();
        pedidoCursoExistente.setQuantidade(pedidoCurso.getQuantidade());
        
        PedidoCurso pedidoCursoAtualizado = pedidoCursoRepository.save(pedidoCursoExistente);
        return ResponseEntity.ok(pedidoCursoAtualizado);
    }

    @DeleteMapping("/{idPedido}/{idCurso}")
    public ResponseEntity<Void> deletarPedidoCurso(@PathVariable Integer idPedido, @PathVariable Integer idCurso) {
        PedidoCursoPK id = new PedidoCursoPK(idPedido, idCurso);
        if (pedidoCursoRepository.existsById(id)) {
            pedidoCursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
