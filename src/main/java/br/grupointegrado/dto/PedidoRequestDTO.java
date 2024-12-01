package br.grupointegrado.dto;

import java.util.Date;

public record PedidoRequestDTO(Integer idUser, Integer idForma, Integer idCurso, Boolean pago, Date dataPedido) {
}
