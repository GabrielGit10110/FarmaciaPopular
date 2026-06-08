CREATE TABLE IF NOT EXISTS fornecedor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cnpj VARCHAR(14),
    endereco VARCHAR(320),
    email VARCHAR(320),
    telefone VARCHAR(11)
);