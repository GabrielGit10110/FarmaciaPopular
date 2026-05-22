package edu.curso.view;

import java.time.LocalDate;

import edu.curso.control.MedicamentoController;
import edu.curso.infraestructure.MedicamentoImplMariaDB;
import edu.curso.model.Medicamento;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UIMedicamentoFX extends Application {
    private final MedicamentoController controller = new MedicamentoController(new MedicamentoImplMariaDB());

    private BorderPane painelPrincipal = new BorderPane();
    private TableView<Medicamento> medicamentos = new TableView<>();

    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblCodBarras = new Label("Codigo de Barras:");
    private TextField txtCodBarras = new TextField();

    private Label lblDataEntrega = new Label("Data de Entrega:");
    private DatePicker dtDataEntrega = new DatePicker(LocalDate.now());

    private Label lblDataValidade = new Label("Data de Validade:");
    private DatePicker dtDataValidade = new DatePicker(LocalDate.now());

    private Label lblFarmPopular = new Label("Farmacia Popular?");
    private CheckBox chkFarmPopular = new CheckBox();

    private Label lblValor = new Label("Valor:");
    private TextField txtValor = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(this.painelPrincipal, 400, 300);

        GridPane painelTopo = new GridPane();
        painelTopo.add()

        stage.setScene(scn);
        stage.show();
    }

}
