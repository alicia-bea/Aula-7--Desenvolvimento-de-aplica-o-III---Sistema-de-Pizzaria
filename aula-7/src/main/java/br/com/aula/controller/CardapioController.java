package br.com.aula.controller;

import br.com.aula.dto.CardapioDto;
import br.com.aula.service.CardapioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    private final CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @PostMapping
    public ResponseEntity<CardapioDto> cadastrar(@RequestBody CardapioDto cardapioDto) {
        CardapioDto salvo = cardapioService.salvar(cardapioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<CardapioDto>> listarTodos() {
        return ResponseEntity.ok(cardapioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardapioDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cardapioService.buscarPorId(id));
    }
}