package com.api.championship.repository;

import com.api.championship.dto.TimeInfoDTO;
import com.api.championship.model.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
    @Query("SELECT new com.api.championship.dto.TimeInfoDTO(t.id, t.nome) " +
           "FROM Campeonato c JOIN c.times t " +
           "WHERE c.id = :campeonatoId")
    List<com.api.championship.dto.TimeInfoDTO> findTimesInfoByCampeonatoId(@Param("campeonatoId") Long campeonatoId);

    @Query("SELECT new com.api.championship.dto.PartidaInfoDTO(p.id, p.data, tm.nome, tv.nome) " +
           "FROM Campeonato c " +
           "JOIN c.partidas p " +
           "JOIN p.timeMandante tm " +
           "JOIN p.timeVisitante tv " +
           "WHERE c.id = :campeonatoId")
    List<com.api.championship.dto.PartidaInfoDTO> findPartidasSimples(@Param("campeonatoId") Long campeonatoId);
    
    @Query("SELECT c FROM Campeonato c JOIN FETCH c.times t WHERE c.id = :campeonatoId")
    Campeonato findByIdWithTimes(@Param("campeonatoId") Long campeonatoId);
    
    @Query("SELECT DISTINCT c FROM Campeonato c " +
           "JOIN FETCH c.partidas p " +
           "LEFT JOIN FETCH p.resultado r " +
           "WHERE c.id = :campeonatoId " +
           "AND p.data < CURRENT_DATE " +
           "AND r IS NOT NULL " +
           "AND r.golsTimeMandante IS NOT NULL " +
           "AND r.golsTimeVisitante IS NOT NULL")
    List<Campeonato> findPartidasOcorridas(@Param("campeonatoId") Long campeonatoId);
    
    @Query("SELECT DISTINCT c FROM Campeonato c " +
           "JOIN FETCH c.partidas p " +
           "LEFT JOIN p.resultado r " +
           "WHERE c.id = :campeonatoId " +
           "AND (p.data > CURRENT_DATE " +
           "OR r IS NULL " +
           "OR r.golsTimeMandante IS NULL " +
           "OR r.golsTimeVisitante IS NULL)")
    List<Campeonato> findPartidasNaoOcorridas(@Param("campeonatoId") Long campeonatoId);
    
    @Query("SELECT new com.api.championship.dto.PartidaInfoDTO(p.id, p.data, tm.nome, tv.nome) " +
           "FROM Campeonato c " +
           "JOIN c.partidas p " +
           "JOIN p.timeMandante tm " +
           "JOIN p.timeVisitante tv " +
           "LEFT JOIN p.resultado r " +
           "WHERE c.id = :campeonatoId " +
           "AND p.data < CURRENT_DATE " +
           "AND r IS NOT NULL " +
           "AND r.golsTimeMandante IS NOT NULL " +
           "AND r.golsTimeVisitante IS NOT NULL")
    List<com.api.championship.dto.PartidaInfoDTO> findPartidasOcorridasInfo(@Param("campeonatoId") Long campeonatoId);
    
    @Query("SELECT new com.api.championship.dto.PartidaInfoDTO(p.id, p.data, tm.nome, tv.nome) " +
           "FROM Campeonato c " +
           "JOIN c.partidas p " +
           "JOIN p.timeMandante tm " +
           "JOIN p.timeVisitante tv " +
           "LEFT JOIN p.resultado r " +
           "WHERE c.id = :campeonatoId " +
           "AND (p.data > CURRENT_DATE " +
           "OR r IS NULL " +
           "OR r.golsTimeMandante IS NULL " +
           "OR r.golsTimeVisitante IS NULL)")
    List<com.api.championship.dto.PartidaInfoDTO> findPartidasNaoOcorridasInfo(@Param("campeonatoId") Long campeonatoId);
}
