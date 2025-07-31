package com.api.championship.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Classe que representa as estatísticas detalhadas de uma partida de futebol.
 * Contém informações como gols, posse de bola, finalizações, cartões, etc.
 */
@Data
@Entity
@Table(name = "resultados")
public class Resultado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Relacionamento um-para-um com a entidade Partida.
     * Cada resultado está associado a uma única partida.
     */
    @OneToOne
    @JoinColumn(name = "partida_id", nullable = false)
    private Partida partida;
    
    // Gols
    private Integer golsTimeMandante;
    private Integer golsTimeVisitante;
    
    // Posse de bola (em %)
    private Integer posseDeBolaMandante;
    private Integer posseDeBolaVisitante;
    
    // Finalizações
    private Integer finalizacoesMandante;
    private Integer finalizacoesVisitante;
    private Integer finalizacoesNoGolMandante;
    private Integer finalizacoesNoGolVisitante;
    
    // Lances
    private Integer escanteiosMandante;
    private Integer escanteiosVisitante;
    private Integer faltasMandante;
    private Integer faltasVisitante;
    private Integer impedimentosMandante;
    private Integer impedimentosVisitante;
    
    // Cartões
    private Integer cartoesAmarelosMandante;
    private Integer cartoesAmarelosVisitante;
    private Integer cartoesVermelhosMandante;
    private Integer cartoesVermelhosVisitante;
    
    /**
     * Data e hora da última atualização do resultado.
     * Atualizada automaticamente antes de persistir ou atualizar.
     */
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    /**
     * Método executado automaticamente antes de persistir ou atualizar a entidade.
     * Atualiza o campo dataAtualizacao com a data/hora atual.
     */
    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.dataAtualizacao = LocalDateTime.now();
    }
}
