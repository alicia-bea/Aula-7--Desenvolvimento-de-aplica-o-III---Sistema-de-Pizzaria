package br.com.aula.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CardapioDto(
    Long id,
    String nome,
    String codigo,
    List<String> ingredientes,
    BigDecimal preco
) {}
