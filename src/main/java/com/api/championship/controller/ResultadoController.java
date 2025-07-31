package com.api.championship.controller;

import com.api.championship.model.Resultado;
import com.api.championship.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping
    public List<Resultado> getAllResultados() {
        return resultadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> getResultadoById(@PathVariable Long id) {
        return resultadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Resultado> createResultado(@RequestBody Resultado resultado) {
        Resultado saved = resultadoService.save(resultado);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> updateResultado(@PathVariable Long id, @RequestBody Resultado resultado) {
        try {
            Resultado updated = resultadoService.update(id, resultado);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultado(@PathVariable Long id) {
        resultadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
