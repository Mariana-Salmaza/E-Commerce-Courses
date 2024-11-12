package br.grupointegrado.dto;

import java.util.Date;

public record PedidoRequestDTO(Integer idPedido, Integer idUser, Integer idPag, Boolean pago, Date dataPedido) {
}