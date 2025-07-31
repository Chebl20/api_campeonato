package com.api.championship.repository;

import com.api.championship.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    // Métodos customizados podem ser adicionados aqui se necessário
}
