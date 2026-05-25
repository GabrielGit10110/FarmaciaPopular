package edu.curso.view;

import edu.curso.control.MedicamentoController;
import edu.curso.infraestructure.MedicamentoImplMariaDB;
import edu.curso.infraestructure.MedicamentoImplMemory;
import edu.curso.model.Medicamento;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
// import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
// import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
// import javafx.util.converter.LocalDateStringConverter;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.util.Optional;

public class UIMedicamentoFX extends Application {
    private final MedicamentoController controller = new MedicamentoController(new MedicamentoImplMemory());
    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblCodBarras = new Label("Codigo de Barras:");
    private TextField txtCodBarras = new TextField();

    private BorderPane painelPrincipal = new BorderPane();
    private Label lblDataEntrega = new Label("Data de Entrega:");
    private DatePicker dtDataEntrega = new DatePicker(LocalDate.now());

    private Label lblDataVencimento = new Label("Data de Vencimento:");
    private DatePicker dtDataVencimento = new DatePicker(LocalDate.now());

    private Label lblFarmPopular = new Label("Farmacia Popular?");
    private CheckBox chkFarmPopular = new CheckBox();

    private Label lblValor = new Label("Valor:");
    private TextField txtValor = new TextField();

    private Button btnSalvar = new Button("SALVAR");

    private TableView<Medicamento> tblMedicamentos = new TableView<>();

    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(this.painelPrincipal, 800, 600);

        Bindings.bindBidirectional(this.txtNome.textProperty(), this.controller.nomeProperty());
        Bindings.bindBidirectional(this.txtCodBarras.textProperty(), this.controller.codBarrasProperty());
        Bindings.bindBidirectional(this.dtDataEntrega.valueProperty(), this.controller.dataEntregaProperty());
        Bindings.bindBidirectional(this.dtDataVencimento.valueProperty(), this.controller.dataVencimentoProperty());
        Bindings.bindBidirectional(this.chkFarmPopular.selectedProperty(), this.controller.farmPopularProperty());
        Bindings.bindBidirectional(this.txtValor.textProperty(), this.controller.valorProperty(), new NumberStringConverter());

        GridPane painelTopo = new GridPane();
        painelTopo.setHgap(10);
        painelTopo.setVgap(10);

        painelTopo.add(this.lblNome, 0, 0);
        painelTopo.add(this.txtNome, 1, 0);
        painelTopo.add(this.btnSalvar, 2, 0);

        painelTopo.add(this.lblCodBarras, 0, 1);
        painelTopo.add(this.txtCodBarras, 1, 1);

        painelTopo.add(this.lblDataEntrega, 0, 2);
        painelTopo.add(this.dtDataEntrega, 1, 2);

        painelTopo.add(this.lblDataVencimento, 0, 3);
        painelTopo.add(this.dtDataVencimento, 1, 3);
        painelTopo.add(this.lblFarmPopular, 0, 4);
        painelTopo.add(this.chkFarmPopular, 1, 4);
        painelTopo.add(this.lblValor, 0, 5);
        painelTopo.add(this.txtValor, 1, 5);

        this.btnSalvar.setOnAction(e -> {
            try {
                this.controller.save();
            } catch (RuntimeException rex) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Erro de input");
                error.setContentText(rex.getMessage());
                error.showAndWait();                
            }
        });

        TableColumn<Medicamento, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Medicamento, String> colCodBarras = new TableColumn<>("Codigo de Barras");
        colCodBarras.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCodBarras())
        );

        TableColumn<Medicamento, String> colDataEntrega = new TableColumn<>("Data de Entrega");
        colDataEntrega.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataEntrega().toString())
        );

        TableColumn<Medicamento, String> colDataVencimento = new TableColumn<>("Data de Vencimento");
        colDataVencimento.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataVencimento().toString())
        );
        //

        TableColumn<Medicamento, String> colFarmPopular = new TableColumn<>("Farmacia Popular");
        colFarmPopular.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().isFarmPopular() ? "Sim" : "Nao")
        );

        TableColumn<Medicamento, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(String.valueOf(itemData.getValue().getValor()))
        );

        TableColumn<Medicamento, Void> colAcoes = new TableColumn<>("Acoes");

        this.tblMedicamentos.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> this.controller.fromEntity(novo)
        );

        this.tblMedicamentos.getColumns().add(colNome);
        this.tblMedicamentos.getColumns().add(colCodBarras);
        this.tblMedicamentos.getColumns().add(colDataEntrega);
        this.tblMedicamentos.getColumns().add(colDataVencimento);
        this.tblMedicamentos.getColumns().add(colFarmPopular);
        this.tblMedicamentos.getColumns().add(colValor);

        this.tblMedicamentos.setItems(this.controller.getLista());

        Callback<TableColumn<Medicamento, Void>, TableCell<Medicamento, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Medicamento, Void> call(TableColumn<Medicamento, Void> column) { 
                    return new TableCell<Medicamento, Void>(){
                        Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction(e -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION, 
                                    "Apagar este Medicamento ?", ButtonType.YES, ButtonType.NO);
                                alert.setTitle("Confirma Deleção");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.isPresent() && result.get() == ButtonType.YES) {
                                    UIMedicamentoFX.this.controller.delete( getIndex() ) ;
                                }
                                
                            });
                        }
                       // 
                        public void updateItem(Void parm, boolean empty) {
                            
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(btnApagar);
                            }
                        }
                    };
                }
        };

        this.tblMedicamentos.getColumns().add(colAcoes);

        colAcoes.setCellFactory( callback );

        this.painelPrincipal.setPadding(new Insets(15));
        this.painelPrincipal.setTop(painelTopo);
        BorderPane.setMargin(painelTopo, new Insets(15));
        this.painelPrincipal.setCenter(this.tblMedicamentos);

        stage.setScene(scn);
        stage.show();
    }
}