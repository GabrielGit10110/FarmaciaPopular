# FarmaciaPopular
Sistema Acadêmico com CRUDS feitos em Java com interface via JavaFX.

# Nome dos Integrantes:
Gabriel de Oliveira Lima Silva
Gabriel de Sousa Ordonho

# Tema escolhido:
Farmácia Popular.

# Descrição do Problema Resolvido:
O sistema busca facilitar o cadastro de medicamentos, fornecedores,
clientes e funcionários de uma farmácia, cuidando da validação dos
campos e considerando quais medicamentos fazem parte do programa
farmácia popular, o que permite que sejam adquiridos de graça por
clientes cadastrados no programa.

# Lista de Entidades Implementadas:
Cliente
Funcionario
Medicamento
Fornecedor

# Instruções para Execução:
- Criar o banco de dados através do script "schema.sql" dentro de
`app/database/`:

```bash
cd app/database

mariadb -u root -p

SOURCE schema.sql
```

- Voltar para a raiz do projeto:

```bash
cd FarmaciaPopular/
```

- Executar o comando `run` do gradle:

```bash
gradle run
```

# Divisão de Responsabilidades por Integrante:
- Criação do README e criação do CRUD com DAO e telas das entidades Medicamento e 
Fornecedor:
Gabriel de Oliveira Lima Silva

- Criação do vídeo e criação do CRUD com DAO e telas das entidades Cliente e Funcionario:
Gabriel de Sousa Ordonho

# Link Para o Vídeo no YouTube:
