package edu.curso.control;

import java.util.List;

import edu.curso.model.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.*;

//
public class FornecedorController {
    private final FornecedorDAO dao;

    private ObservableList<Fornecedor> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty cnpj = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");

    public FornecedorController(FornecedorDAO dao) {
        this.dao = dao;
        load();
    }

    public void clearFields() {
        this.id.set(0);
        this.nome.set("");
        this.cnpj.set("");
        this.endereco.set("");
        this.telefone.set("");
        this.email.set("");
    }

    public void fromEntity(Fornecedor f) {
        if (f != null) {
            this.id.set( f.getId() );
            this.nome.set( f.getNome() );
            this.cnpj.set( f.getCnpj() );
            this.endereco.set( f.getEndereco() );
            this.telefone.set( f.getTelefone() );
            this.email.set( f.getEmail() );
        }
    }

    public Fornecedor toEntity() {
        Fornecedor f = new Fornecedor(
                                    this.id.get(),
                                    this.nome.get(),
                                    this.cnpj.get(),
                                    this.endereco.get(),
                                    this.telefone.get(),
                                    this.email.get()
                                );
        return f;
    }

    // copiar isso para outros CRUDS e f.dar as variaveis (mensagem para o Gabes)
    private void validate(Fornecedor f) {
        if (f.getNome() == null || f.getNome().isBlank()) {
            throw new RuntimeException("Nome nao pode ser vazio");
        }

        // nao sei colocar acentos, entao os remedios nao tem acentos
        if (!f.getNome().matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Nome nao pode conter numeros ou simbolos");
        }

        if (f.getCnpj() == null || f.getCnpj().isBlank()) {
            throw new RuntimeException("Cnpj nao pode ser vazio");
        }

        if (!f.getCnpj().matches("\\d+")) {
            throw new RuntimeException("Cnpj so pode conter numeros");
        }

        if (f.getEndereco() == null || f.getEndereco().isBlank()) {
            throw new RuntimeException("Endereco nao pode ser vazio");
        }

        if (!f.getEndereco().matches("[a-za-Z0-9 ]+")) {
            throw new RuntimeException("Endereco so pode contar letras, espacos e numeros");
        }

        if (f.getTelefone() == null || f.getTelefone().isBlank()) {
            throw new RuntimeException("Telefone nao pode ser vazio");
        }

        if (!f.getTelefone().matches("\\d+")) {
            throw new RuntimeException("Telefone so pode conter numeros");
        }

        if (f.getEmail() == null || f.getEmail().isBlank()) {
            throw new RuntimeException("Email nao pode ser vazio");
        }

        if (!f.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+")) {
            throw new RuntimeException("Email nao pode conter espacos, e so usa os seguintes simbolo '.-_' e numeros");
        }

    }

    public void save() {
        Fornecedor f = toEntity();
        validate(f);

        if (this.id.get() > 0) {
            System.out.println("Atualizando Fornecedor...\n" + f.toString());
            this.dao.update(id.get(), f);
        } else {
            System.out.println("Salvando Novo Fornecedor...\n" + f.toString());
            this.dao.save(f);
        }

        clearFields();
        load();
    }

    public void delete(int indice) {
        Fornecedor f = this.lista.get(indice);
        System.out.println("Deletando f.dicamento...\n" + f.toString());
        this.dao.delete(f);
        load();
    }

    public Fornecedor findById(long id) {
        return this.dao.findById(id);
    }

    public List<Fornecedor> findByNome(String nome) {
        return this.dao.findByNome(nome);
    }

    public Fornecedor findByCnpj(String cnpj) {
        return this.dao.findByCnpj(cnpj);
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

    public String getCnpj() { return this.cnpj.get(); }
    public StringProperty cnpjProperty() { return this.cnpj; }

    public String getTelefone() { return this.telefone.get(); }
    public StringProperty telefoneProperty() { return this.telefone; }

    public String getEmail() { return this.email.get(); }
    public StringProperty emailProperty() { return this.email; }

    public ObservableList<Fornecedor> getLista() { return this.lista; }
}