CREATE DATABASE IF NOT EXISTS farmacia;
USE farmacia;

-- importar tabelas
SOURCE medicamento.sql;
SOURCE fornecedor.sql;
SOURCE funcionario.sql;
SOURCE cliente.sql;