package edu.curso.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.*;

import edu.curso.model.Funcionario;

public class FuncionarioController {
    private final FuncionarioDAO dao;

    private ObservableList<Funcionario> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty registro = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty cargo = new SimpleStringProperty("");

    public FuncionarioController(FuncionarioDAO dao) {
        this.dao = dao;
        load();
    }

    public void clearFields() {
        this.id.set(0);
        this.nome.set("");
        this.telefone.set("");
        this.registro.set("");
        this.email.set("");
        this.cargo.set("");
        load();
    }

    public void fromEntity(Funcionario fu) {
        if (fu != null) {
            this.id.set( fu.getId() );
            this.nome.set( fu.getNome() );
            this.telefone.set( fu.getTelefone() );
            this.registro.set( fu.getRegistro() );
            this.email.set( fu.getEmail() );
            this.cargo.set( fu.getCargo() );
        }
    }

    public Funcionario toEntity() {
        Funcionario fu = new Funcionario(
                                    this.id.get(),
                                    this.nome.get(),
                                    this.telefone.get(),
                                    this.registro.get(),
                                    this.email.get(),
                                    this.cargo.get()
                                );
        return fu;

    }

      private void validate(Funcionario fu) {

        if (fu.getNome() == null || fu.getNome().isBlank()) {
            throw new RuntimeException("Nome nao pode ser vazio");
        }

        if (!fu.getNome().matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Nome nao pode conter numeros ou simbolos");
        }

         if (fu.getTelefone() == null || fu.getTelefone().isBlank()) {
            System.out.println("Telefone nao pode ser vazio");
            throw new RuntimeException("Telefone nao pode ser vazio");
        }

        if (fu.getTelefone() != null && fu.getTelefone().length() != 11) {
            System.out.println("Numero de telefone deve conter 11 digitos (2 do DDD e 9 do numero)");
            throw new RuntimeException("Numero de telefone deve conter 11 digitos (2 do DDD e 9 do numero)");
        }

        if (!fu.getTelefone().matches("\\d+")) {
            System.out.println("Telefone so pode conter numeros");
            throw new RuntimeException("Telefone so pode conter numeros");
        }

        if (fu.getRegistro() == null || fu.getRegistro().isBlank()) {
            throw new RuntimeException("Registro nao pode ser vazio");
        }

        if (fu.getRegistro() != null && fu.getRegistro().length() != 8) {
            System.out.println("Numero do registro deve conter 8 digitos");
            throw new RuntimeException("Numero de registro deve conter 8 digitos");
        }

        if (!fu.getRegistro().matches("\\d+")) {
            System.out.println("Registro so pode conter numeros");
            throw new RuntimeException("Registro so pode conter numeros");
        }

        if (fu.getEmail() == null || fu.getEmail().isBlank()) {
            System.out.println("Email nao pode ser vazio");
            throw new RuntimeException("Email nao pode ser vazio");
        }

        if (fu.getEmail() != null && fu.getEmail().length() > 320) {
            System.out.println("Endereco de email nao pode ser maior que 320 caracteres");
            throw new RuntimeException("Endereco de email nao pode ser maior que 320 caracteres");
        }

        if (!fu.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+")) {
            System.out.println("Email nao pode conter espacos, e so usa os seguintes simbolo '.-_' e numeros");
            throw new RuntimeException("Email nao pode conter espacos, e deve seguir o seguinte formato: nome.Do_Email123@provedor.com");
        }

        if (fu.getCargo() == null || fu.getCargo().isBlank()) {
            throw new RuntimeException("Cargo nao pode ser vazio");
        }
    }

    public void update() {
        Funcionario fu = toEntity();
        validate(fu);

        Funcionario existe = this.dao.findById(id.get());

        if (existe == null) {
            throw new RuntimeException("Funcionario nao existe no banco. Clique em NOVO para criar.");
        }

        this.dao.update(id.get(), fu);
        System.out.println("Atualizando Funcionario...\n" + fu.toString());

        clearFields();
        load();
    }

    
    public void save() {
        Funcionario fu = toEntity();
        validate(fu);

        this.dao.save(fu);
        System.out.println("Salvando Novo Funcionario...\n" + fu.toString());

        clearFields();
        load();
    }

    public void delete(int indice) {
        Funcionario fu = this.lista.get(indice);
        this.dao.delete(fu);
        System.out.println("Deletando Funcionario...\n" + fu.toString());
        clearFields();
        load();
    }

    public Funcionario findById() {
        return this.dao.findById(getId());
    }

    public void findByName() {
        this.lista.setAll(this.dao.findByName(getNome()));
    }

    public void findByRegistro() {
        this.lista.setAll(this.dao.findByRegistro(getRegistro()));
    }

    public void load() {
        this.lista.clear();
        this.lista.addAll(
            this.dao.findAll()
        );
    }

    public long getId() { return this.id.get(); }
    public LongProperty idProperty() { return this.id; }

    public String getNome() { return this.nome.get(); }
    public StringProperty nomeProperty() { return this.nome; }

    public String getTelefone() { return this.telefone.get(); }
    public StringProperty telefoneProperty() { return this.telefone; }

    public String getRegistro() { return this.registro.get(); }
    public StringProperty registroProperty() { return this.registro; }

    public String getEmail() { return this.email.get(); }
    public StringProperty emailProperty() { return this.email; }

    public String getCargo() { return this.cargo.get(); }
    public StringProperty cargoProperty() { return this.cargo; }

    public ObservableList<Funcionario> getLista() { return this.lista; }

}