package br.grupointegrado.dto;

import java.math.BigDecimal;

public record CursoRequestDTO(Integer idCurso, String nomeCurso, BigDecimal valorCurso, String descricao) {
}