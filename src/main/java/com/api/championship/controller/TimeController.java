package com.api.championship.controller;

import com.api.championship.dto.TimeDTO;
import com.api.championship.model.Time;
import com.api.championship.repository.TimeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/times")
@RequiredArgsConstructor
public class TimeController {
    
    private final TimeRepository timeRepository;
    private final ModelMapper modelMapper;
    
    @GetMapping
    public List<TimeDTO> listarTodos() {
        return timeRepository.findAll().stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TimeDTO> buscarPorId(@PathVariable Long id) {
        return timeRepository.findById(id)
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public TimeDTO criar(@Valid @RequestBody TimeDTO timeDTO) {
        Time time = modelMapper.map(timeDTO, Time.class);
        Time timeSalvo = timeRepository.save(time);
        return modelMapper.map(timeSalvo, TimeDTO.class);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TimeDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TimeDTO timeDTO) {
        if (!timeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Time time = modelMapper.map(timeDTO, Time.class);
        time.setId(id);
        Time timeAtualizado = timeRepository.save(time);
        return ResponseEntity.ok(modelMapper.map(timeAtualizado, TimeDTO.class));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!timeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
