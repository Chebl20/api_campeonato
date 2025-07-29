package com.api.championship.controller;

import com.api.championship.dto.EstadioDTO;
import com.api.championship.model.Estadio;
import com.api.championship.repository.EstadioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estadios")
public class EstadioController {

    @Autowired
    private EstadioRepository estadioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<EstadioDTO> listarTodos() {
        List<Estadio> estadios = estadioRepository.findAll();
        return estadios.stream()
                .map(estadio -> modelMapper.map(estadio, EstadioDTO.class))
                .collect(Collectors.toList());
    }
}
