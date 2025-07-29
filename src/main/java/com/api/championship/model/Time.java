package com.api.championship.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "times")
public class Time {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do time é obrigatório")
    private String nome;
    
    @NotBlank(message = "A cidade do time é obrigatória")
    private String cidade;
    
    @ManyToOne
    @JoinColumn(name = "estadio_id")
    @JsonBackReference
    private Estadio estadioSede;
    
    @OneToMany(mappedBy = "time")
    private List<Jogador> jogadores;
    
    @ManyToMany(mappedBy = "times")
    private List<Campeonato> campeonatos;
    
    @OneToMany(mappedBy = "timeMandante")
    private List<Partida> partidasMandante;
    
    @OneToMany(mappedBy = "timeVisitante")
    private List<Partida> partidasVisitante;
}
