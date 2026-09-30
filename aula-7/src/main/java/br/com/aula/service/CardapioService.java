package br.com.aula.service;

import br.com.aula.dto.CardapioDto;
import br.com.aula.model.Cardapio;
import br.com.aula.repository.CardapioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardapioService {

    private final CardapioRepository cardapioRepository;

    public CardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    @Transactional
    public CardapioDto salvar(CardapioDto dto) {
        Cardapio cardapio = new Cardapio(
                null,
                dto.nome(),
                dto.codigo(),
                dto.ingredientes(),
                dto.preco()
        );
        cardapio = cardapioRepository.save(cardapio);
        return toDto(cardapio);
    }

    @Transactional(readOnly = true)
    public List<CardapioDto> listarTodos() {
        return cardapioRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public CardapioDto buscarPorId(Long id) {
        Cardapio cardapio = cardapioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item de cardápio não encontrado com o ID: " + id));
        return toDto(cardapio);
    }

    public CardapioDto toDto(Cardapio cardapio) {
        return new CardapioDto(
                cardapio.getId(),
                cardapio.getNome(),
                cardapio.getCodigo(),
                cardapio.getIngredientes(),
                cardapio.getPreco()
        );
    }
}