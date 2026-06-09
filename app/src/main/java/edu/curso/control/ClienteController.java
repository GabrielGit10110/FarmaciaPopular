package edu.curso.control;

import edu.curso.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.*;

import java.time.LocalDate;

//finalizado
public class ClienteController {
    private final ClienteDAO dao;

    private ObservableList<Cliente> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty cpf = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<>(LocalDate.now());
    private StringProperty rPopular = new SimpleStringProperty("");

    public ClienteController(ClienteDAO dao) {
        this.dao = dao;
        load();
    }

    public void clearFields() {
        this.id.set(0);
        this.nome.set("");
        this.cpf.set("");
        this.endereco.set("");
        this.telefone.set("");
        this.dataNascimento.set(LocalDate.now());
        this.rPopular.set("");

        load();
    }

    public void fromEntity(Cliente c) {
        if (c != null) {
            this.id.set( c.getId() );
            this.nome.set( c.getNome() );
            this.cpf.set( c.getCpf() );
            this.endereco.set( c.getEndereco() );
            this.telefone.set( c.getTelefone() );
            this.dataNascimento.set( c.getDataNascimento() );
            this.rPopular.set( c.getRPopular() );
        }
    }

    public Cliente toEntity() {
        Cliente c = new Cliente(
                                    this.id.get(),
                                    this.nome.get(),
                                    this.cpf.get(),
                                    this.endereco.get(),
                                    this.telefone.get(),
                                    this.dataNascimento.get(),
                                    this.rPopular.get()
                                );
        return c;
    }

    private void validate(Cliente c) {
        if (c.getNome() == null || c.getNome().isBlank()) {
            throw new RuntimeException("Nome nao pode ser vazio");
        }

        if (!c.getNome().matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Nome nao pode conter numeros ou simbolos");
        }

        if (c.getEndereco() == null || c.getEndereco().isBlank()) {
            throw new RuntimeException("O endereco nao pode ser vazio");
        }

        if (!c.getEndereco().matches("[a-zA-Z0-9 ]+")) {
            System.out.println("Endereco so pode contar letras, espacos e numeros");
            throw new RuntimeException("Endereco so pode contar letras, espacos e numeros");
        }

        if (c.getTelefone() == null || c.getTelefone().isBlank()) {
            throw new RuntimeException("O telefone nao pode ser vazio");
        }


        if (c.getTelefone() != null && c.getTelefone().length() != 11) {
            System.out.println("Numero de telefone deve conter 11 digitos (2 do DDD e 9 do numero)");
            throw new RuntimeException("Numero de telefone deve conter 11 digitos (2 do DDD e 9 do numero)");
        }

        if (!c.getTelefone().matches("\\d+")) {
            throw new RuntimeException("O telefone so pode conter numeros");
        }

        if (c.getCpf() == null || c.getCpf().isBlank()) {
            throw new RuntimeException("CPF nao pode ser vazio");
        }

        if (c.getCpf().length() != 11) {
            throw new RuntimeException("CPF deve possuir 11 caracteres");
        }

        if (!c.getCpf().matches("\\d+")) {
            throw new RuntimeException("CPF so pode conter numeros");
        }
    }

    public void update() {
        Cliente c = toEntity();
        validate(c);

        Cliente existe = this.dao.searchById(id.get());

        if (existe == null) {
            throw new RuntimeException("Cliente nao existe no banco. Clique em 'NOVO' para adiciona-lo");
        }

        this.dao.update(id.get(), c);
        System.out.println("Atualizando Cliente...\n" + c.toString());

        clearFields();
        load();
    }

    public void save() {
        Cliente c = toEntity();
        validate(c);

        System.out.println("Salvando Novo Cliente...\n" + c.toString());
        this.dao.save(c);

        clearFields();
        load();
    }

    public void delete(int indice) {
        Cliente c = this.lista.get(indice);
        System.out.println("Deletando Cliente...\n" + c.toString());
        this.dao.delete(c);
        load();
    }

    public Cliente searchById(long id) {
        return this.dao.searchById(id);
    }

    public void searchByName() {
        this.lista.setAll(this.dao.searchByName(getNome()));
    }

    public void searchByCpf() {
        this.lista.setAll(this.dao.searchByCpf(getCpf()));
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

    public String getCpf() { return this.cpf.get(); }
    public StringProperty cpfProperty() { return this.cpf; }

    public String getEndereco() { return this.endereco.get(); }
    public StringProperty enderecoProperty() { return this.endereco; }

    public String getTelefone() { return this.telefone.get(); }
    public StringProperty telefoneProperty() { return this.telefone; }

    public LocalDate getDataNascimento() { return this.dataNascimento.get(); }
    public ObjectProperty<LocalDate> dataNascimentoProperty() { return this.dataNascimento; }

    public String getRPopular() { return this.rPopular.get(); }
    public StringProperty rPopularProperty() { return this.rPopular; }


    public ObservableList<Cliente> getLista() { return this.lista; }
}

