package br.grupointegrado.dto;

import java.util.Date;

public record AuditoriaRequestDTO(Integer id, Date data, String tabelaAfetada, String acao, String motivo,
                                  String dadosAnteriores, String dadosNovos) {
}