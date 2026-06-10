package edu.curso.model;

public class Funcionario {
    private long id;
    private String nome;
    private String telefone;
    private String registro;
    private String email;
    private String cargo;

    public Funcionario() {
        this.id = 0;
        this.nome = "";
        this.telefone = "";
        this.registro = "";
        this.email = "";
        this.cargo = "";
    }

    public Funcionario(long id, String nome, String telefone, String registro, String email, String cargo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.registro = registro;
        this.email = email;
        this.cargo = cargo;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getRegistro() { return registro; }
    public void setRegistro(String registro) { this.registro = registro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        StringBuilder entity = new StringBuilder();

        entity.append("ID no Banco: ").append(this.id).append("\n");
        entity.append("Funcionário: ").append(this.nome).append("\n");
        entity.append("Telefone: ").append(this.telefone).append("\n");
        entity.append("Registro: ").append(this.registro).append("\n");
        entity.append("Email: ").append(this.email).append("\n");
        entity.append("Cargo: ").append(this.cargo).append("\n");

        return entity.toString();
    }

}
