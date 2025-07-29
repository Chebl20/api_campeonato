package com.api.championship.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("timestamp", LocalDateTime.now().toString());

        try {
            // Testar conexão com o banco de dados
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            response.put("database", "CONNECTED");
            
            // Verificar versão do banco de dados
            String dbVersion = jdbcTemplate.queryForObject(
                "SELECT valor FROM configuracao_sistema WHERE chave = 'VERSAO_BANCO'", 
                String.class
            );
            response.put("databaseVersion", dbVersion);
            
            // Contar registros em tabelas importantes
            Map<String, Object> counts = new HashMap<>();
            counts.put("estadios", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM estadios", Integer.class));
            counts.put("times", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM times", Integer.class));
            counts.put("jogadores", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM jogadores", Integer.class));
            counts.put("campeonatos", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM campeonatos", Integer.class));
            counts.put("partidas", jdbcTemplate.queryForObject("SELECT COUNT(*) FROM partidas", Integer.class));
            
            response.put("recordCounts", counts);
            
            logger.info("Health check realizado com sucesso");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Erro ao verificar saúde da aplicação", e);
            response.put("database", "ERROR: " + e.getMessage());
            return ResponseEntity.status(503).body(response);
        }
    }
}
