package br.grupointegrado.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PagamentoRequestDTO(
        Integer idPag, Integer idPedido, BigDecimal valorPedido, Date dataPagamento, String statusPagamento, List<Integer> idsFormasPagamento) {
}
