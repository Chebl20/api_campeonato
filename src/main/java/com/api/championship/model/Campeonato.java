package com.api.championship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "campeonatos")
public class Campeonato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do campeonato é obrigatório")
    private String nome;
    
    @NotNull(message = "O ano do campeonato é obrigatório")
    private Integer ano;
    
    @ManyToMany
    @JoinTable(
        name = "campeonato_times",
        joinColumns = @JoinColumn(name = "campeonato_id"),
        inverseJoinColumns = @JoinColumn(name = "time_id")
    )
    private List<Time> times;
    
    @OneToMany(mappedBy = "campeonato")
    private List<Partida> partidas;
}
