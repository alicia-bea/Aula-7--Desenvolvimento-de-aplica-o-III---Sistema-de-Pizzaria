package br.com.aula.repository;

import br.com.aula.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    Optional<Cardapio> findByCodigo(String codigo);
}