package edu.curso.view;

import edu.curso.control.FornecedorController;
// import edu.curso.infraestructure.FornecedorImplMariaDB;
import edu.curso.infraestructure.FornecedorImplMemory;
import edu.curso.model.Fornecedor;
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

public class UIFornecedorFX extends Application {
    private final FornecedorController controller = new FornecedorController(new FornecedorImplMemory());
    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblCnpj = new Label("Codigo de Barras:");
    private TextField txtCnpj = new TextField();

    private BorderPane painelPrincipal = new BorderPane();
    private Label lblEndereco = new Label("Endereco:");
    private TextField txtEndereco = new TextField();

    private Label lblTelefone = new Label("Farmacia Popular?");
    private TextField txtTelefone = new TextField();

    private Label lblEmail = new Label("Email:");
    private TextField txtEmail = new TextField();

    private Button btnSalvar = new Button("SALVAR");

    private TableView<Fornecedor> tblFornecedores = new TableView<>();

    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(this.painelPrincipal, 800, 600);

        Bindings.bindBidirectional(this.txtNome.textProperty(), this.controller.nomeProperty());
        Bindings.bindBidirectional(this.txtCnpj.textProperty(), this.controller.cnpjProperty());
        Bindings.bindBidirectional(this.txtEndereco.textProperty(), this.controller.());
        Bindings.bindBidirectional(this.txtEmail.valueProperty(), this.controller.dataVencimentoProperty());
        Bindings.bindBidirectional(this.txtTelefone.selectedProperty(), this.controller.farmPopularProperty());
        Bindings.bindBidirectional(this.txtValor.textProperty(), this.controller.valorProperty(), new NumberStringConverter());

        GridPane painelTopo = new GridPane();
        painelTopo.setHgap(10);
        painelTopo.setVgap(10);

        painelTopo.add(this.lblNome, 0, 0);
        painelTopo.add(this.txtNome, 1, 0);
        painelTopo.add(this.btnSalvar, 2, 0);

        painelTopo.add(this.lblCnpj, 0, 1);
        painelTopo.add(this.txtCnpj, 1, 1);

        painelTopo.add(this.lblEndereco, 0, 2);
        painelTopo.add(this.txtEndereco, 1, 2);

        painelTopo.add(this.lblDataVencimento, 0, 3);
        painelTopo.add(this.txtEmail, 1, 3);
        painelTopo.add(this.lblTelefone, 0, 4);
        painelTopo.add(this.txtTelefone, 1, 4);
        painelTopo.add(this.lblValor, 0, 5);
        painelTopo.add(this.txtValor, 1, 5);

        this.btnSalvar.setOnAction(e -> {
            try {
                this.controller.save();
                this.tblFornecedores.getSelectionModel().clearSelection();
            } catch (RuntimeException rex) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Erro de input");
                error.setContentText(rex.getMessage());
                error.showAndWait();                
            }
        });

        TableColumn<Fornecedor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Fornecedor, String> colCodBarras = new TableColumn<>("Codigo de Barras");
        colCodBarras.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCodBarras())
        );

        TableColumn<Fornecedor, String> colDataEntrega = new TableColumn<>("Data de Entrega");
        colDataEntrega.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataEntrega().toString())
        );

        TableColumn<Fornecedor, String> colDataVencimento = new TableColumn<>("Data de Vencimento");
        colDataVencimento.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataVencimento().toString())
        );
        //

        TableColumn<Fornecedor, String> colFarmPopular = new TableColumn<>("Farmacia Popular");
        colFarmPopular.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().isFarmPopular() ? "Sim" : "Nao")
        );

        TableColumn<Fornecedor, String> colValor = new TableColumn<>("Valor");
        colValor.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(String.valueOf(itemData.getValue().getValor()))
        );

        TableColumn<Fornecedor, Void> colAcoes = new TableColumn<>("Acoes");

        this.tblFornecedores.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> this.controller.fromEntity(novo)
        );

        this.tblFornecedores.getColumns().add(colNome);
        this.tblFornecedores.getColumns().add(colCodBarras);
        this.tblFornecedores.getColumns().add(colDataEntrega);
        this.tblFornecedores.getColumns().add(colDataVencimento);
        this.tblFornecedores.getColumns().add(colFarmPopular);
        this.tblFornecedores.getColumns().add(colValor);

        this.tblFornecedores.setItems(this.controller.getLista());

        Callback<TableColumn<Fornecedor, Void>, TableCell<Fornecedor, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Fornecedor, Void> call(TableColumn<Fornecedor, Void> column) { 
                    return new TableCell<Fornecedor, Void>(){
                        Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction(e -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION, 
                                    "Apagar este Fornecedor ?", ButtonType.YES, ButtonType.NO);
                                alert.setTitle("Confirma Deleção");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.isPresent() && result.get() == ButtonType.YES) {
                                    UIFornecedorFX.this.controller.delete( getIndex() ) ;
                                    tblFornecedores.getSelectionModel().clearSelection();
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

        this.tblFornecedores.getColumns().add(colAcoes);

        colAcoes.setCellFactory( callback );

        this.painelPrincipal.setOnMouseClicked(event -> {
            if (!tblFornecedores.isHover()) {
                tblFornecedores.getSelectionModel().clearSelection();
                controller.clearFields();
            }
        });

        this.painelPrincipal.setPadding(new Insets(15));
        this.painelPrincipal.setTop(painelTopo);
        BorderPane.setMargin(painelTopo, new Insets(15));
        this.painelPrincipal.setCenter(this.tblFornecedores);

        stage.setScene(scn);
        stage.show();
    }
}