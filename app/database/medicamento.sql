CREATE TABLE IF NOT EXISTS medicamento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    codigo_de_barras VARCHAR(50),
    data_de_entrega DATE,
    data_de_vencimento DATE,
    farmacia_popular BOOLEAN,
    valor DOUBLE
);