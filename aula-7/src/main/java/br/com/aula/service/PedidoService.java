package br.com.aula.service;

import br.com.aula.dto.ItemPedidoDto;
import br.com.aula.dto.PedidoDto;
import br.com.aula.model.Cardapio;
import br.com.aula.model.Cliente;
import br.com.aula.model.ItemPedidoModel;
import br.com.aula.model.Pedido;
import br.com.aula.repository.CardapioRepository;
import br.com.aula.repository.ClienteRepository;
import br.com.aula.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final CardapioRepository cardapioRepository;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         CardapioRepository cardapioRepository,
                         ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.cardapioRepository = cardapioRepository;
        this.itemPedidoService = itemPedidoService;
    }

    @Transactional
    public PedidoDto criarPedido(PedidoDto dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + dto.clienteId()));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataHora(LocalDateTime.now());

        for (ItemPedidoDto itemDto : dto.itens()) {
            Cardapio cardapio = cardapioRepository.findById(itemDto.cardapioId())
                    .orElseThrow(() -> new RuntimeException("Pizza não encontrada com ID: " + itemDto.cardapioId()));

            ItemPedidoModel item = new ItemPedidoModel(
                    pedido,
                    cardapio,
                    itemDto.quantidade(),
                    cardapio.getPreco()
            );

            pedido.adicionarItem(item);
        }

        pedido = pedidoRepository.save(pedido);
        return toDto(pedido);
    }

    @Transactional(readOnly = true)
    public List<PedidoDto> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoDto buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
        return toDto(pedido);
    }

    private PedidoDto toDto(Pedido pedido) {
        List<ItemPedidoDto> itensDto = pedido.getItens().stream()
                .map(itemPedidoService::toDto)
                .toList();

        return new PedidoDto(
                pedido.getId(),
                pedido.getCliente().getId(),
                pedido.getCliente().getNome(),
                pedido.getDataHora(),
                pedido.getValorTotal(),
                itensDto
        );
    }
}