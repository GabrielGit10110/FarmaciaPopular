package edu.curso.control;

import edu.curso.model.Medicamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.*;
import java.time.LocalDate;

//
public class MedicamentoController {
    private final MedicamentoDAO dao;

    private ObservableList<Medicamento> lista = FXCollections.observableArrayList();

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty codBarras = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataEntrega = new SimpleObjectProperty<>(LocalDate.now());
    private ObjectProperty<LocalDate> dataVencimento = new SimpleObjectProperty<>(LocalDate.now());
    private BooleanProperty farmPopular = new SimpleBooleanProperty(false);
    private DoubleProperty valor = new SimpleDoubleProperty(0.0);

    public MedicamentoController(MedicamentoDAO dao) {
        this.dao = dao;
        load();
    }

    public void clearFields() {
        this.id.set(0);
        this.nome.set("");
        this.codBarras.set("");
        this.dataEntrega.set(LocalDate.now());
        this.dataVencimento.set(LocalDate.now());
        this.farmPopular.set(false);
        this.valor.set(0);
        load();
    }

    public void fromEntity(Medicamento m) {
        if (m != null) {
            this.id.set( m.getId() );
            this.nome.set( m.getNome() );
            this.codBarras.set( m.getCodBarras() );
            this.dataEntrega.set( m.getDataEntrega() );
            this.dataVencimento.set( m.getDataVencimento() );
            this.farmPopular.set( m.isFarmPopular() );
            this.valor.set( m.getValor() );
        }
    }

    public Medicamento toEntity() {
        Medicamento m = new Medicamento(
                                    this.id.get(),
                                    this.nome.get(),
                                    this.codBarras.get(),
                                    this.dataEntrega.get(),
                                    this.dataVencimento.get(),
                                    this.farmPopular.get(),
                                    this.valor.get()
                                );
        return m;
    }

    private void validate(Medicamento m) {
        if (m.getNome() == null || m.getNome().isBlank()) {
            throw new RuntimeException("Nome nao pode ser vazio");
        }

        if (!m.getNome().matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Nome nao pode conter numeros ou simbolos");
        }

        if (m.getCodBarras() == null || m.getCodBarras().isBlank()) {
            throw new RuntimeException("Codigo de barras nao pode ser vazio");
        }

        if (m.getCodBarras().length() != 13) {
            throw new RuntimeException("Codigo de barras deve possuir 13 caracteres");
        }

        if (!m.getCodBarras().matches("\\d+")) {
            throw new RuntimeException("Codigo de barras so pode conter numeros");
        }

        if (m.getValor() <= 0) {
            throw new RuntimeException("Valor deve ser maior que 0");
        }
    }

    public void update() {
        Medicamento m = toEntity();
        validate(m);

        Medicamento existe = this.dao.searchById(id.get());

        if (existe == null) {
            throw new RuntimeException("Medicamento nao existe no banco. Clique em 'NOVO' para adiciona-lo");
        }

        this.dao.update(id.get(), m);
        System.out.println("Atualizando Medicamento...\n" + m.toString());

        clearFields();
        load();
    }

    public void save() {
        Medicamento m = toEntity();
        validate(m);

        System.out.println("Salvando Novo Medicamento...\n" + m.toString());
        this.dao.save(m);

        clearFields();
        load();
    }

    public void delete(int indice) {
        Medicamento m = this.lista.get(indice);
        System.out.println("Deletando medicamento...\n" + m.toString());
        this.dao.delete(m);
        load();
    }

    public Medicamento searchById(long id) {
        return this.dao.searchById(id);
    }

    public void searchByName() {
        this.lista.setAll(this.dao.searchByName(getNome()));
    }

    public void searchByCode() {
        this.lista.setAll(this.dao.searchByCode(getCodBarras()));
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

    public String getCodBarras() { return this.codBarras.get(); }
    public StringProperty codBarrasProperty() { return this.codBarras; }

    public LocalDate getDataEntrega() { return this.dataEntrega.get(); }
    public ObjectProperty<LocalDate> dataEntregaProperty() { return this.dataEntrega; }

    public LocalDate getDataVencimento() { return this.dataVencimento.get(); }
    public ObjectProperty<LocalDate> dataVencimentoProperty() { return this.dataVencimento; }

    public boolean isFarmPopular() { return this.farmPopular.get(); }
    public BooleanProperty farmPopularProperty() { return this.farmPopular; }

    public double getValor() { return this.valor.get(); }
    public DoubleProperty valorProperty() { return this.valor; }

    public ObservableList<Medicamento> getLista() { return this.lista; }
}