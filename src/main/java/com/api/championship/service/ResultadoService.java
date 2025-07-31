package com.api.championship.service;

import com.api.championship.model.Resultado;
import com.api.championship.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    public List<Resultado> findAll() {
        return resultadoRepository.findAll();
    }

    public Optional<Resultado> findById(Long id) {
        return resultadoRepository.findById(id);
    }

    public Resultado save(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    public Resultado update(Long id, Resultado novoResultado) {
        return resultadoRepository.findById(id)
                .map(resultado -> {
                    novoResultado.setId(id);
                    return resultadoRepository.save(novoResultado);
                })
                .orElseThrow(() -> new RuntimeException("Resultado não encontrado"));
    }

    public void delete(Long id) {
        resultadoRepository.deleteById(id);
    }
}
