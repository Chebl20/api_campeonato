package com.api.championship.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "jogadores")
public class Jogador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do jogador é obrigatório")
    private String nome;
    
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate nascimento;
    
    @NotNull(message = "A altura é obrigatória")
    @Positive(message = "A altura deve ser um valor positivo")
    private Double altura;
    
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;
}
