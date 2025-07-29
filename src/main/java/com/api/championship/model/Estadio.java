package com.api.championship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Data
@Entity
@Table(name = "estadios")
public class Estadio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do estádio é obrigatório")
    private String nome;
    
    @NotBlank(message = "O endereço do estádio é obrigatório")
    private String endereco;
    
    @OneToMany(mappedBy = "estadioSede")
    @JsonManagedReference
    private List<Time> times;
    
    @JsonIgnore
    @OneToMany(mappedBy = "estadio")
    private List<Partida> partidas;
}
