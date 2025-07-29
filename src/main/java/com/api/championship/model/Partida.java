package com.api.championship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "partidas")
public class Partida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "A data da partida é obrigatória")
    private LocalDateTime data;
    
    @ManyToOne
    @JoinColumn(name = "time_mandante_id")
    private Time timeMandante;
    
    @ManyToOne
    @JoinColumn(name = "time_visitante_id")
    private Time timeVisitante;
    
    @ManyToOne
    @JoinColumn(name = "estadio_id")
    private Estadio estadio;
    
    private Integer golsTimeMandante;
    
    private Integer golsTimeVisitante;
    
    @ManyToOne
    @JoinColumn(name = "campeonato_id")
    private Campeonato campeonato;
}
