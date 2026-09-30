package br.com.aula.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
    Long id,
    Long clienteId,
    String nomeCliente,
    LocalDateTime dataHora,
    BigDecimal valorTotal,
    List<ItemPedidoDto> itens
) {}