package edu.curso.model;

public class Fornecedor {
    private long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;

    public Fornecedor() {
        this.id = 0;
        this.nome = "";
        this.cnpj = "";
        this.endereco = "";
        this.telefone = "";
        this.email = "";
    }

    public Fornecedor(long id, String nome, String cnpj, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Fornecedor(String nome, String cnpj, String endereco, String telefone, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        StringBuilder entity = new StringBuilder();

        entity.append("ID no Banco: ").append(this.id).append("\n");
        entity.append("Fornecedor: ").append(this.nome).append("\n");
        entity.append("CNPJ: ").append(this.cnpj).append("\n");
        entity.append("Endereco: ").append(this.endereco).append("\n");
        entity.append("Telefone: ").append(this.telefone).append("\n");
        entity.append("Email: ").append(this.email).append("\n");

        return entity.toString();
    }
}
//