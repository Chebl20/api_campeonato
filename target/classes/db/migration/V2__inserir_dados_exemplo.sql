-- Inserir estádios com dados completos
INSERT INTO estadios (nome, endereco, data_criacao, data_atualizacao) VALUES
('Maracanã', 'Av. Pres. Castelo Branco, Portão 3 - Maracanã, Rio de Janeiro - RJ', '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Morumbi', 'Praça Roberto Gomes Pedrosa, 1 - Morumbi, São Paulo - SP', '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Mineirão', 'Av. Antônio Abrahão Caram, 1001 - São José, Belo Horizonte - MG', '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Arena do Grêmio', 'Av. Padre Leopoldo Brentano, 110 - Humaitá, Porto Alegre - RS', '2023-01-01 00:00:00', CURRENT_TIMESTAMP);

-- Inserir times com dados completos
INSERT INTO times (nome, cidade, estadio_id, data_criacao, data_atualizacao) VALUES
('Flamengo', 'Rio de Janeiro', 1, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('São Paulo', 'São Paulo', 2, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Cruzeiro', 'Belo Horizonte', 3, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Grêmio', 'Porto Alegre', 4, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Fluminense', 'Rio de Janeiro', 1, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Palmeiras', 'São Paulo', 2, '2023-01-01 00:00:00', CURRENT_TIMESTAMP);

-- Inserir jogadores com dados completos
INSERT INTO jogadores (nome, nascimento, altura, time_id, data_criacao, data_atualizacao) VALUES
-- Jogadores do Flamengo
('Pedro', '1997-06-20', 1.85, 1, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Gabriel Barbosa', '1996-08-30', 1.78, 1, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Arrascaeta', '1994-06-01', 1.72, 1, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
-- Jogadores do São Paulo
('Lucas Moura', '1992-08-13', 1.75, 2, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Calleri', '1993-09-14', 1.86, 2, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
-- Jogadores do Cruzeiro
('Fábio', '1980-09-30', 1.88, 3, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Gilberto', '1993-03-07', 1.85, 3, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
-- Jogadores do Grêmio
('Luis Suárez', '1987-01-24', 1.82, 4, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Kannemann', '1991-03-14', 1.87, 4, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
-- Jogadores do Fluminense
('Germán Cano', '1988-01-02', 1.78, 5, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('André', '2001-07-16', 1.76, 5, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
-- Jogadores do Palmeiras
('Raphael Veiga', '1995-06-19', 1.76, 6, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Dudu', '1992-01-07', 1.66, 6, '2023-01-01 00:00:00', CURRENT_TIMESTAMP);

-- Inserir campeonatos com dados completos
INSERT INTO campeonatos (nome, ano, data_criacao, data_atualizacao) VALUES
('Campeonato Brasileiro Série A', 2023, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Copa do Brasil', 2023, '2023-01-01 00:00:00', CURRENT_TIMESTAMP),
('Libertadores', 2023, '2023-01-01 00:00:00', CURRENT_TIMESTAMP);

-- Inserir times nos campeonatos
INSERT INTO campeonato_times (campeonato_id, time_id, data_inscricao) VALUES
-- Brasileirão
(1, 1, '2023-01-15'), (1, 2, '2023-01-15'), (1, 3, '2023-01-15'), (1, 4, '2023-01-15'), (1, 5, '2023-01-15'), (1, 6, '2023-01-15'),
-- Copa do Brasil
(2, 1, '2023-01-15'), (2, 2, '2023-01-15'), (2, 3, '2023-01-15'), (2, 4, '2023-01-15'), (2, 5, '2023-01-15'), (2, 6, '2023-01-15'),
-- Libertadores
(3, 1, '2023-01-15'), (3, 2, '2023-01-15'), (3, 3, '2023-01-15'), (3, 4, '2023-01-15');

-- Inserir partidas com dados completos
INSERT INTO partidas (data, time_mandante_id, time_visitante_id, estadio_id, gols_time_mandante, gols_time_visitante, campeonato_id, rodada, data_criacao, data_atualizacao) VALUES
-- Rodada 1 Brasileirão
('2023-04-15 16:00:00', 1, 2, 1, 2, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Flamengo 2x1 São Paulo
('2023-04-15 19:00:00', 3, 4, 3, 0, 2, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Cruzeiro 0x2 Grêmio
('2023-04-16 16:00:00', 5, 6, 1, 1, 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Fluminense 1x1 Palmeiras
-- Rodada 2 Brasileirão
('2023-04-22 16:00:00', 2, 3, 2, 1, 1, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- São Paulo 1x1 Cruzeiro
('2023-04-22 19:00:00', 4, 5, 4, 3, 0, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Grêmio 3x0 Fluminense
('2023-04-23 16:00:00', 6, 1, 2, 0, 2, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);  -- Palmeiras 0x2 Flamengo

-- Atualizar a tabela de auditoria
INSERT INTO auditoria (acao, tabela_afetada, dados_novos, usuario) 
VALUES ('MIGRACAO', 'BANCO_DE_DADOS', 'Dados de exemplo inseridos/atualizados com sucesso na migração V2', 'SISTEMA');

-- Atualizar a versão do banco de dados
UPDATE configuracao_sistema 
SET valor = '1.1.0', 
    descricao = 'Versão com estrutura atualizada e dados de exemplo',
    data_atualizacao = CURRENT_TIMESTAMP
WHERE chave = 'VERSAO_BANCO';

UPDATE configuracao_sistema 
SET valor = CURRENT_TIMESTAMP,
    data_atualizacao = CURRENT_TIMESTAMP
WHERE chave = 'ULTIMA_ATUALIZACAO';
