package br.com.aula.DTO;

public record ClienteDto(
    Long id,
    String nome,
    String telefone,
    String enderecoEntrega
) {}