package com.api.championship.repository;

import com.api.championship.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {
    
    @Query("SELECT t FROM Time t WHERE t.id IN " +
           "(SELECT t2.id FROM Campeonato c JOIN c.times t2 WHERE c.id = :campeonatoId)")
    List<Time> findAllByCampeonatoId(@Param("campeonatoId") Long campeonatoId);
}
