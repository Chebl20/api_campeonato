package com.api.championship.repository;

import com.api.championship.model.Jogador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    Page<Jogador> findByTimeId(Long timeId, Pageable pageable);
}
