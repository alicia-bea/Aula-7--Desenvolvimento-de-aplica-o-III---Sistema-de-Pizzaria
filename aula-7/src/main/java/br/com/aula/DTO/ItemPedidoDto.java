package br.com.aula.DTO;

import java.math.BigDecimal;

public record ItemPedidoDto(
    Long id,
    Long cardapioId,
    String nomePizza,
    Integer quantidade,
    BigDecimal precoUnitario,
    BigDecimal subtotal
) {}