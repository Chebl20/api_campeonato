package com.api.championship.controller;

import com.api.championship.dto.CampeonatoDTO;
import com.api.championship.model.Campeonato;
import com.api.championship.model.Time;
import com.api.championship.repository.CampeonatoRepository;
import com.api.championship.service.CampeonatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/campeonatos")
public class CampeonatoController {
    
    private final CampeonatoRepository campeonatoRepository;
    private final CampeonatoService campeonatoService;
    private final ModelMapper modelMapper;
    
    public CampeonatoController(CampeonatoRepository campeonatoRepository, 
                              CampeonatoService campeonatoService,
                              ModelMapper modelMapper) {
        this.campeonatoRepository = campeonatoRepository;
        this.campeonatoService = campeonatoService;
        this.modelMapper = modelMapper;
        System.out.println("CampeonatoController inicializado com ModelMapper: " + (this.modelMapper != null));
    }
    
    @GetMapping
    public List<CampeonatoDTO> listarTodos() {
        return campeonatoRepository.findAll().stream()
                .map(campeonato -> modelMapper.map(campeonato, CampeonatoDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CampeonatoDTO> buscarPorId(@PathVariable Long id) {
        return campeonatoRepository.findById(id)
                .map(campeonato -> ResponseEntity.ok(modelMapper.map(campeonato, CampeonatoDTO.class)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Campeonato criar(@Valid @RequestBody Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> atualizar(@PathVariable Long id, @Valid @RequestBody Campeonato campeonato) {
        if (!campeonatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campeonato.setId(id);
        return ResponseEntity.ok(campeonatoRepository.save(campeonato));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!campeonatoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campeonatoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/times")
    public List<Time> listarTimes(@PathVariable Long id) {
        return campeonatoService.listarTimesDoCampeonato(id);
    }
    
    @GetMapping("/{id}/partidas/ocorridas")
    public List<Campeonato> listarPartidasOcorridas(@PathVariable Long id) {
        return campeonatoService.listarPartidasOcorridas(id);
    }
    
    @GetMapping("/{id}/partidas/nao-ocorridas")
    public List<Campeonato> listarPartidasNaoOcorridas(@PathVariable Long id) {
        return campeonatoService.listarPartidasNaoOcorridas(id);
    }
    
    @GetMapping("/{id}/tabela")
    public List<CampeonatoService.TimeClassificacao> getTabelaCampeonato(@PathVariable Long id) {
        return campeonatoService.getTabelaCampeonato(id);
    }
}
