CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cpf VARCHAR(14),
    endereco VARCHAR(320),
    telefone VARCHAR(11),
    data_nascimento DATE,
    rPopular VARCHAR(11)
);