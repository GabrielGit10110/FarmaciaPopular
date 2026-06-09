CREATE TABLE IF NOT EXISTS funcionario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    telefone VARCHAR(11),
    registro VARCHAR(20),
    email VARCHAR(200),
    cargo VARCHAR(100)
);