package edu.curso.model;

import java.time.LocalDate;

public class Cliente {
    private long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private LocalDate dataNascimento;
    private String rPopular;

    public Cliente() {
        this.id = 0;
        this.nome = "";
        this.cpf = "";
        this.endereco = "";
        this.telefone = "";
        this.dataNascimento = LocalDate.now();
        this.rPopular = "";
    }

    public Cliente(long id, String nome, String cpf, String endereco, String telefone, LocalDate dataNascimento, String rPopular) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.rPopular = rPopular;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getRPopular() { return rPopular; }
    public void setRPopular(String rPopular) { this.rPopular = rPopular; }

    @Override
    public String toString() {
        StringBuilder entity = new StringBuilder();

        entity.append("ID no Banco: ").append(this.id).append("\n");
        entity.append("Cliente: ").append(this.nome).append("\n");
        entity.append("CPF: ").append(this.cpf).append("\n");
        entity.append("Endereço: ").append(this.endereco).append("\n");
        entity.append("Telefone: ").append(this.telefone).append("\n");
        entity.append("Data de Nascimento: ").append(this.dataNascimento).append("\n");
        entity.append("Registro Popular: ").append(this.rPopular).append("\n");

        return entity.toString();
    }
}
