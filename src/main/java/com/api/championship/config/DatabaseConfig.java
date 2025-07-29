package com.api.championship.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);

    @Bean
    public CommandLineRunner checkDatabaseConnection(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        return args -> {
            try (Connection connection = dataSource.getConnection()) {
                // Verifica a conexão
                DatabaseMetaData metaData = connection.getMetaData();
                logger.info("Conexão com o banco de dados estabelecida com sucesso!");
                logger.info("URL: {}", metaData.getURL());
                logger.info("Usuário: {}", metaData.getUserName());
                logger.info("Driver: {} {}", metaData.getDriverName(), metaData.getDriverVersion());

                // Lista as tabelas existentes
                List<String> tables = new ArrayList<>();
                ResultSet rs = metaData.getTables(null, null, "%", new String[]{"TABLE"});
                while (rs.next()) {
                    tables.add(rs.getString("TABLE_NAME"));
                }
                logger.info("Tabelas encontradas: {}", tables);

                // Executa uma consulta de teste
                try {
                    String dbProductName = metaData.getDatabaseProductName();
                    String dbProductVersion = metaData.getDatabaseProductVersion();
                    logger.info("Banco de dados: {} {}", dbProductName, dbProductVersion);
                    
                    // Testa uma consulta simples
                    jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
                    logger.info("Teste de consulta ao banco de dados realizado com sucesso!");
                } catch (Exception e) {
                    logger.error("Erro ao testar consulta ao banco de dados:", e);
                }

            } catch (Exception e) {
                logger.error("Falha ao conectar ao banco de dados:", e);
                throw new RuntimeException("Falha ao conectar ao banco de dados", e);
            }
        };
    }
}
