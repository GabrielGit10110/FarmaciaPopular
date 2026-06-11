package edu.curso.view;

import edu.curso.control.FuncionarioController;
import edu.curso.infraestructure.FuncionarioImplMariaDB;
import edu.curso.model.Funcionario;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.util.Optional;

public class UIFuncionarioFX implements UI {
    private final FuncionarioController controller = new FuncionarioController(new FuncionarioImplMariaDB());
    
    private BorderPane painelPrincipal = new BorderPane();
     
    private Label lblTitulo = new Label("Funcionario");

    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblRegistro = new Label("Registro:");
    private TextField txtRegistro = new TextField();

    private Label lblTelefone = new Label("Telefone:");
    private TextField txtTelefone = new TextField();

    private Label lblEmail = new Label("Email:");
    private TextField txtEmail = new TextField();

    private Label lblCargo = new Label("Cargo:");
    private TextField txtCargo = new TextField();

    private Button btnNovo = new Button("NOVO");
    private Button btnAtualizar = new Button("ATUALIZAR");
    private Button bntLimpar = new Button("LIMPAR");
    private Button btnPesquisarPorNome = new Button("PESQUISAR POR NOME");
    private Button btnPesquisarRegistro = new Button("PESQUISAR POR REGISTRO");

    private TableView<Funcionario> tblFuncionarios = new TableView<>();

    @Override
    public Pane render() {
        Bindings.bindBidirectional(this.txtNome.textProperty(), this.controller.nomeProperty());
        Bindings.bindBidirectional(this.txtTelefone.textProperty(), this.controller.telefoneProperty());
        Bindings.bindBidirectional(this.txtRegistro.textProperty(), this.controller.registroProperty());
        Bindings.bindBidirectional(this.txtEmail.textProperty(), this.controller.emailProperty());
        Bindings.bindBidirectional(this.txtCargo.textProperty(), this.controller.cargoProperty());

        GridPane painelTopo = new GridPane();
        painelTopo.setHgap(10);
        painelTopo.setVgap(10);

        this.lblTitulo.setStyle("-fx-font-size: 20px");
        painelTopo.add(this.lblTitulo, 0, 0);

        painelTopo.add(this.lblNome, 0, 1);
        painelTopo.add(this.txtNome, 1, 1);
        painelTopo.add(this.btnNovo, 2, 1);
        painelTopo.add(this.btnAtualizar, 3, 1);

        painelTopo.add(this.lblTelefone, 0, 2);
        painelTopo.add(this.txtTelefone, 1, 2);
        painelTopo.add(this.bntLimpar, 2, 2);

        painelTopo.add(this.lblRegistro, 0, 3);
        painelTopo.add(this.txtRegistro, 1, 3);
        painelTopo.add(this.btnPesquisarPorNome, 2, 3);

        painelTopo.add(this.lblEmail, 0, 4);
        painelTopo.add(this.txtEmail, 1, 4);
        painelTopo.add(this.btnPesquisarRegistro, 2, 4);

        painelTopo.add(this.lblCargo, 0, 5);
        painelTopo.add(this.txtCargo, 1, 5);

        this.btnNovo.setOnAction(e -> {
            try {
                this.controller.save();
                this.tblFuncionarios.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Novo Funcionario");
                success.setContentText("Funcionario salvo!");
                success.showAndWait();
            } catch (RuntimeException rex) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Erro de input");
                error.setContentText(rex.getMessage());
                error.showAndWait();                
            }

        });

        this.btnAtualizar.setOnAction(e -> {
            try {
                this.controller.update();
                this.tblFuncionarios.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Alteracao Concluida");
                success.setContentText("Funcionario teve dados alterados!");
                success.showAndWait();
            } catch (RuntimeException rex) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Erro de input");
                error.setContentText(rex.getMessage());
                error.showAndWait();                
            }
        });


        this.bntLimpar.setOnAction(e -> {
            this.controller.clearFields();
        });

        this.btnPesquisarPorNome.setOnAction(e -> {
            String nome = this.controller.getNome();

            if (nome == null || nome.isBlank()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERRO");
                alert.setContentText("Nome nao pode ser vazio...");
                alert.showAndWait();
            } else {
                this.controller.findByName();
            }

        });

        this.btnPesquisarRegistro.setOnAction(e -> {
            String registro = this.controller.getRegistro();

            if (registro == null || registro.isBlank() || registro.length() != 8) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERRO");
                alert.setContentText("Registro nao pode ser vazio e deve conter 8 caracteres");
                alert.showAndWait();
            } else {
                this.controller.findByRegistro();
            }

        });

        TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Funcionario, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTelefone())
        );

        TableColumn<Funcionario, String> colRegistro = new TableColumn<>("Registro");
        colRegistro.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getRegistro())
        );

        TableColumn<Funcionario, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail())
        );

        TableColumn<Funcionario, String> colCargo = new TableColumn<>("Cargo");
        colCargo.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCargo())
        );

        TableColumn<Funcionario, Void> colAcoes = new TableColumn<>("Acoes");

        this.tblFuncionarios.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> this.controller.fromEntity(novo)
        );

        this.tblFuncionarios.getColumns().add(colNome);
        this.tblFuncionarios.getColumns().add(colTelefone);
        this.tblFuncionarios.getColumns().add(colRegistro);
        this.tblFuncionarios.getColumns().add(colEmail);
        this.tblFuncionarios.getColumns().add(colCargo);
        
        this.tblFuncionarios.setItems(this.controller.getLista());

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Funcionario, Void> call(TableColumn<Funcionario, Void> column) { 
                    return new TableCell<Funcionario, Void>(){
                        Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction(e -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION, 
                                    "Apagar este Fornecedor ?", ButtonType.YES, ButtonType.NO);
                                alert.setTitle("Confirma Deleção");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.isPresent() && result.get() == ButtonType.YES) {
                                    UIFuncionarioFX.this.controller.delete( getIndex() ) ;
                                    tblFuncionarios.getSelectionModel().clearSelection();
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

        this.tblFuncionarios.getColumns().add(colAcoes);

        colAcoes.setCellFactory( callback );


        this.painelPrincipal.setPadding(new Insets(15));
        this.painelPrincipal.setTop(painelTopo);
        BorderPane.setMargin(painelTopo, new Insets(15));
        this.painelPrincipal.setCenter(this.tblFuncionarios);

        return this.painelPrincipal;
    }

}
