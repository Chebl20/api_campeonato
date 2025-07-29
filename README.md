# API de Gerenciamento de Campeonato de Futebol

API REST para gerenciamento de campeonatos de futebol, desenvolvida com Spring Boot.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.1.2
- Spring Data JPA
- PostgreSQL/H2 Database
- Flyway
- Lombok
- ModelMapper
- Bean Validation
- SpringDoc OpenAPI (Swagger)

## Funcionalidades

- CRUD completo para Campeonatos, Times, Jogadores e Estádios
- Listagem de times de um campeonato
- Listagem de partidas ocorridas e não ocorridas
- Geração de tabela de classificação por vitórias e saldo de gols
- Paginação na listagem de jogadores
- Documentação da API com Swagger/OpenAPI

## Como Executar

1. Clone o repositório
2. Configure o banco de dados PostgreSQL ou use o H2 (configurado para ambiente de desenvolvimento)
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

## Documentação da API

A documentação da API está disponível através do Swagger UI:
- http://localhost:8080/swagger-ui.html

## Endpoints Principais

### Campeonatos
- GET /api/campeonatos - Lista todos os campeonatos
- GET /api/campeonatos/{id} - Busca um campeonato por ID
- POST /api/campeonatos - Cria um novo campeonato
- PUT /api/campeonatos/{id} - Atualiza um campeonato
- DELETE /api/campeonatos/{id} - Remove um campeonato
- GET /api/campeonatos/{id}/times - Lista times de um campeonato
- GET /api/campeonatos/{id}/partidas/ocorridas - Lista partidas já ocorridas
- GET /api/campeonatos/{id}/partidas/nao-ocorridas - Lista partidas não ocorridas
- GET /api/campeonatos/{id}/tabela - Gera tabela de classificação

### Times
- GET /api/times - Lista todos os times
- GET /api/times/{id} - Busca um time por ID
- POST /api/times - Cria um novo time
- PUT /api/times/{id} - Atualiza um time
- DELETE /api/times/{id} - Remove um time

### Jogadores
- GET /api/jogadores - Lista todos os jogadores (paginado)
- GET /api/jogadores/{id} - Busca um jogador por ID
- POST /api/jogadores - Cria um novo jogador
- PUT /api/jogadores/{id} - Atualiza um jogador
- DELETE /api/jogadores/{id} - Remove um jogador
- GET /api/jogadores/time/{timeId} - Lista jogadores de um time (paginado)

## Modelo de Dados

O sistema gerencia as seguintes entidades:

- Campeonato (nome, ano, times participantes)
- Time (nome, cidade, estádio-sede, jogadores)
- Estádio (nome, endereço)
- Jogador (nome, data nascimento, altura, time)
- Partida (data, time mandante, time visitante, estádio, gols)
