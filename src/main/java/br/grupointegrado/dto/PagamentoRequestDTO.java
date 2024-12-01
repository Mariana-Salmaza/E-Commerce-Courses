package br.grupointegrado.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record PagamentoRequestDTO(
    Integer idPedido,
    BigDecimal valorPedido,
    String statusPagamento,
    Date dataPagamento,
    List<Integer> idsFormasPagamento
) {}
