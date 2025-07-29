package com.api.championship.controller;

import com.api.championship.dto.JogadorDTO;
import com.api.championship.model.Jogador;
import com.api.championship.repository.JogadorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jogadores")
@RequiredArgsConstructor
public class JogadorController {
    
    private final JogadorRepository jogadorRepository;
    private final ModelMapper modelMapper;
    
    @GetMapping
    public Page<JogadorDTO> listarTodos(Pageable pageable) {
        return jogadorRepository.findAll(pageable)
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<JogadorDTO> buscarPorId(@PathVariable Long id) {
        return jogadorRepository.findById(id)
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public JogadorDTO criar(@Valid @RequestBody JogadorDTO jogadorDTO) {
        Jogador jogador = modelMapper.map(jogadorDTO, Jogador.class);
        Jogador jogadorSalvo = jogadorRepository.save(jogador);
        return modelMapper.map(jogadorSalvo, JogadorDTO.class);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<JogadorDTO> atualizar(@PathVariable Long id, @Valid @RequestBody JogadorDTO jogadorDTO) {
        if (!jogadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Jogador jogador = modelMapper.map(jogadorDTO, Jogador.class);
        jogador.setId(id);
        Jogador jogadorAtualizado = jogadorRepository.save(jogador);
        return ResponseEntity.ok(modelMapper.map(jogadorAtualizado, JogadorDTO.class));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!jogadorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        jogadorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/time/{timeId}")
    public Page<JogadorDTO> listarPorTime(@PathVariable Long timeId, Pageable pageable) {
        return jogadorRepository.findByTimeId(timeId, pageable)
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class));
    }
}
