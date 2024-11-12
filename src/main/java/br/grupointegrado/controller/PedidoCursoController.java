package br.grupointegrado.controller;

import br.grupointegrado.dto.PedidoCursoRequestDTO;
import br.grupointegrado.model.PedidoCurso;
import br.grupointegrado.model.Pedido;
import br.grupointegrado.model.Curso;
import br.grupointegrado.repository.PedidoCursoRepository;
import br.grupointegrado.repository.PedidoRepository;
import br.grupointegrado.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedido-cursos")
public class PedidoCursoController {

    @Autowired
    private PedidoCursoRepository pedidoCursoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private PedidoCursoRequestDTO converterParaDTO(PedidoCurso pedidoCurso) {
        return new PedidoCursoRequestDTO(
                pedidoCurso.getCurso().getIdCurso(),
                pedidoCurso.getPedido().getIdPedido(),
                pedidoCurso.getQuantidade()
        );
    }

    private PedidoCurso converterParaEntidade(PedidoCursoRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        PedidoCurso pedidoCurso = new PedidoCurso();
        pedidoCurso.setCurso(curso);
        pedidoCurso.setPedido(pedido);
        pedidoCurso.setQuantidade(dto.quantidade());
        return pedidoCurso;
    }

    @PostMapping
    public ResponseEntity<PedidoCursoRequestDTO> criarPedidoCurso(@RequestBody PedidoCursoRequestDTO pedidoCursoDTO) {
        PedidoCurso pedidoCurso = converterParaEntidade(pedidoCursoDTO);
        PedidoCurso novoPedidoCurso = pedidoCursoRepository.save(pedidoCurso);
        PedidoCursoRequestDTO novoPedidoCursoDTO = converterParaDTO(novoPedidoCurso);
        return ResponseEntity.ok(novoPedidoCursoDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoCursoRequestDTO>> listarPedidoCursos() {
        List<PedidoCurso> pedidoCursos = pedidoCursoRepository.findAll();
        List<PedidoCursoRequestDTO> pedidoCursosDTO = pedidoCursos.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidoCursosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoCursoRequestDTO> buscarPedidoCurso(@PathVariable Integer id) {
        PedidoCurso pedidoCurso = pedidoCursoRepository.findById(id).orElse(null);
        if (pedidoCurso == null) {
            return ResponseEntity.notFound().build();
        }
        PedidoCursoRequestDTO pedidoCursoDTO = converterParaDTO(pedidoCurso);
        return ResponseEntity.ok(pedidoCursoDTO);
    }

    // Atualizar PedidoCurso
    @PutMapping("/{id}")
    public ResponseEntity<PedidoCursoRequestDTO> atualizarPedidoCurso(@PathVariable Integer id, @RequestBody PedidoCursoRequestDTO pedidoCursoDTO) {
        PedidoCurso pedidoCurso = pedidoCursoRepository.findById(id).orElse(null);
        if (pedidoCurso == null) {
            return ResponseEntity.notFound().build();
        }

        Curso curso = cursoRepository.findById(pedidoCursoDTO.idCurso())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        Pedido pedido = pedidoRepository.findById(pedidoCursoDTO.idPedido())
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedidoCurso.setCurso(curso);
        pedidoCurso.setPedido(pedido);
        pedidoCurso.setQuantidade(pedidoCursoDTO.quantidade());

        PedidoCurso pedidoCursoAtualizado = pedidoCursoRepository.save(pedidoCurso);
        PedidoCursoRequestDTO pedidoCursoAtualizadoDTO = converterParaDTO(pedidoCursoAtualizado);
        return ResponseEntity.ok(pedidoCursoAtualizadoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedidoCurso(@PathVariable Integer id) {
        if (pedidoCursoRepository.existsById(id)) {
            pedidoCursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
