-- Limpeza de dados para evitar violação de chave primária
DELETE FROM resultados;
DELETE FROM partidas;
DELETE FROM campeonato_times;
DELETE FROM campeonatos;
DELETE FROM jogadores;
DELETE FROM times;
DELETE FROM estadios;

-- Inserção de estádios
INSERT INTO estadios (id, nome, endereco) VALUES 
(1, 'Maracanã', 'Rua Professor Eurico Rabelo, Portão 18 - Maracanã, Rio de Janeiro - RJ'),
(2, 'Morumbi', 'Praça Roberto Gomes Pedrosa, 1 - Morumbi, São Paulo - SP'),
(3, 'Mineirão', 'Avenida Antônio Abrahão Caram, 1001 - São José, Belo Horizonte - MG'),
(4, 'Arena do Grêmio', 'Avenida Padre Leopoldo Brentano, 110 - Humaitá, Porto Alegre - RS'),
(5, 'Neo Química Arena', 'Rua Cmte. Ivan Ribeiro de Castro, 102 - Vila Cruzeiro, São Paulo - SP');

-- Inserção de times
INSERT INTO times (id, nome, cidade, estadio_id) VALUES
(1, 'Flamengo', 'Rio de Janeiro', 1),
(2, 'São Paulo', 'São Paulo', 2),
(3, 'Atlético Mineiro', 'Belo Horizonte', 3),
(4, 'Grêmio', 'Porto Alegre', 4),
(5, 'Corinthians', 'São Paulo', 5);

-- Inserção de jogadores (exemplo para 2 times)
-- Flamengo
INSERT INTO jogadores (id, nome, nascimento, altura, time_id) VALUES
(1, 'Hugo Souza', '2000-01-31', 1.94, 1),
(2, 'Filipe Luís', '1985-08-09', 1.82, 1),
(3, 'David Luiz', '1987-04-22', 1.89, 1),
(4, 'Gerson', '1997-05-20', 1.76, 1),
(5, 'Gabigol', '1996-08-30', 1.78, 1);

-- São Paulo
INSERT INTO jogadores (id, nome, nascimento, altura, time_id) VALUES
(6, 'Tiago Volpi', '1990-10-15', 1.92, 2),
(7, 'Reinaldo', '1988-09-10', 1.77, 2),
(8, 'Miranda', '1984-09-07', 1.86, 2),
(9, 'Dani Alves', '1983-05-06', 1.72, 2),
(10, 'Luciano', '1993-07-18', 1.80, 2);

-- Inserção de campeonatos
INSERT INTO campeonatos (id, nome, ano) VALUES
(1, 'Brasileirão Série A', 2023),
(2, 'Copa do Brasil', 2023);

-- Relacionamento entre times e campeonatos
INSERT INTO campeonato_times (campeonato_id, time_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),  -- Todos os times no Brasileirão
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5);  -- Todos os times na Copa do Brasil

-- Inserção de partidas (algumas já ocorridas, outras não)
-- Partidas do Brasileirão
INSERT INTO partidas (id, data, time_mandante_id, time_visitante_id, estadio_id, campeonato_id) VALUES
-- Partidas já ocorridas
(1, '2023-05-10 16:00:00', 1, 2, 1, 1),  -- Flamengo x São Paulo
(2, '2023-05-11 19:30:00', 3, 4, 3, 1),  -- Atlético-MG x Grêmio
-- Partidas futuras
(3, '2023-12-10 16:00:00', 1, 5, 1, 1),  -- Flamengo x Corinthians
(4, '2023-12-11 19:30:00', 2, 3, 2, 1),  -- São Paulo x Atlético-MG
(5, '2023-12-12 21:00:00', 4, 5, 4, 1);  -- Grêmio x Corinthians

-- Inserção de resultados (apenas para partidas já ocorridas)
INSERT INTO resultados (
    partida_id, gols_time_mandante, gols_time_visitante,
    posse_de_bola_mandante, posse_de_bola_visitante,
    finalizacoes_mandante, finalizacoes_visitante,
    finalizacoes_no_gol_mandante, finalizacoes_no_gol_visitante,
    escanteios_mandante, escanteios_visitante,
    faltas_mandante, faltas_visitante,
    impedimentos_mandante, impedimentos_visitante,
    cartoes_amarelos_mandante, cartoes_amarelos_visitante,
    cartoes_vermelhos_mandante, cartoes_vermelhos_visitante,
    data_atualizacao
) VALUES
-- Resultado Flamengo 2 x 1 São Paulo
(1, 2, 1, 55, 45, 15, 10, 7, 4, 8, 5, 12, 15, 2, 1, 2, 3, 0, 1, '2023-05-10 18:15:00'),
-- Resultado Atlético-MG 1 x 1 Grêmio
(2, 1, 1, 60, 40, 12, 8, 5, 3, 6, 4, 10, 14, 3, 2, 1, 2, 0, 0, '2023-05-11 21:25:00');
