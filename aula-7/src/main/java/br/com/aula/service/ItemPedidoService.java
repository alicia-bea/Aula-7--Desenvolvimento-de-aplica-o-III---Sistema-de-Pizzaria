package br.com.aula.service;

import br.com.aula.dto.ItemPedidoDto;
import br.com.aula.model.ItemPedidoModel;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    public ItemPedidoDto toDto(ItemPedidoModel item) {
        return new ItemPedidoDto(
                item.getId(),
                item.getCardapio().getId(),
                item.getCardapio().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getSubtotal()
        );
    }
}