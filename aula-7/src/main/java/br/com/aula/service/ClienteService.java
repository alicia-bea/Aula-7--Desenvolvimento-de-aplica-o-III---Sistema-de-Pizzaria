package br.com.aula.service;

import br.com.aula.dto.ClienteDto;
import br.com.aula.model.Cliente;
import br.com.aula.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteDto salvar(ClienteDto dto) {
        Cliente cliente = new Cliente(null, dto.nome(), dto.telefone(), dto.enderecoEntrega());
        cliente = clienteRepository.save(cliente);
        return toDto(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDto> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteDto buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
        return toDto(cliente);
    }

    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEnderecoEntrega()
        );
    }
}