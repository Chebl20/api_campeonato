-- Criação da tabela de estádios
CREATE TABLE IF NOT EXISTS estadios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Criação da tabela de times
CREATE TABLE IF NOT EXISTS times (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estadio_id BIGINT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (estadio_id) REFERENCES estadios(id)
);

-- Criação da tabela de jogadores
CREATE TABLE IF NOT EXISTS jogadores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nascimento DATE NOT NULL,
    altura DOUBLE NOT NULL,
    time_id BIGINT,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (time_id) REFERENCES times(id)
);

-- Criação da tabela de campeonatos
CREATE TABLE IF NOT EXISTS campeonatos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    ano INTEGER NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de relacionamento muitos-para-muitos entre times e campeonatos
CREATE TABLE IF NOT EXISTS campeonato_times (
    campeonato_id BIGINT NOT NULL,
    time_id BIGINT NOT NULL,
    data_inscricao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (campeonato_id, time_id),
    FOREIGN KEY (campeonato_id) REFERENCES campeonatos(id) ON DELETE CASCADE,
    FOREIGN KEY (time_id) REFERENCES times(id) ON DELETE CASCADE
);

-- Criação da tabela de partidas
CREATE TABLE IF NOT EXISTS partidas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data TIMESTAMP NOT NULL,
    time_mandante_id BIGINT NOT NULL,
    time_visitante_id BIGINT NOT NULL,
    estadio_id BIGINT,
    campeonato_id BIGINT NOT NULL,
    gols_time_mandante INTEGER,
    gols_time_visitante INTEGER,
    rodada INTEGER,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (time_mandante_id) REFERENCES times(id) ON DELETE CASCADE,
    FOREIGN KEY (time_visitante_id) REFERENCES times(id) ON DELETE CASCADE,
    FOREIGN KEY (estadio_id) REFERENCES estadios(id) ON DELETE SET NULL,
    FOREIGN KEY (campeonato_id) REFERENCES campeonatos(id) ON DELETE CASCADE,
    CONSTRAINT check_different_teams CHECK (time_mandante_id != time_visitante_id)
);

-- Índices para melhorar performance
CREATE INDEX idx_partidas_data ON partidas(data);
CREATE INDEX idx_partidas_campeonato ON partidas(campeonato_id);
CREATE INDEX idx_partidas_times ON partidas(time_mandante_id, time_visitante_id);
CREATE INDEX idx_jogadores_time ON jogadores(time_id);

-- Tabela de auditoria para logs
CREATE TABLE IF NOT EXISTS auditoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    acao VARCHAR(50) NOT NULL,
    tabela_afetada VARCHAR(50) NOT NULL,
    id_registro BIGINT,
    dados_anteriores TEXT,
    dados_novos TEXT,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario VARCHAR(100)
);

-- Tabela para armazenar configurações do sistema
CREATE TABLE IF NOT EXISTS configuracao_sistema (
    chave VARCHAR(50) PRIMARY KEY,
    valor TEXT,
    descricao VARCHAR(255),
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Inserir configurações iniciais
INSERT INTO configuracao_sistema (chave, valor, descricao) VALUES 
('VERSAO_BANCO', '1.0.0', 'Versão atual do esquema do banco de dados'),
('ULTIMA_ATUALIZACAO', CURRENT_TIMESTAMP, 'Data e hora da última atualização do banco');

-- Log de inicialização
INSERT INTO auditoria (acao, tabela_afetada, dados_novos, usuario) 
VALUES ('INICIALIZACAO', 'BANCO_DE_DADOS', 'Banco de dados inicializado com sucesso', 'SISTEMA');
