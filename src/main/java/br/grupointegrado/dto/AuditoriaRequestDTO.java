package br.grupointegrado.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AuditoriaRequestDTO(Integer idAuditoria, Integer idCurso, LocalDate data, BigDecimal valorAntigo, BigDecimal valorNovo, String motivo) {
}
