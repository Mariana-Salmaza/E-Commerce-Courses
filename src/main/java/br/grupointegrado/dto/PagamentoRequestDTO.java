package br.grupointegrado.dto;

import java.math.BigDecimal;
import java.util.Date;

public record PagamentoRequestDTO(
        Integer idPag, BigDecimal valorPedido, Date dataPagamento, String statusPagamento, Integer idFormaPagamento) {
}
