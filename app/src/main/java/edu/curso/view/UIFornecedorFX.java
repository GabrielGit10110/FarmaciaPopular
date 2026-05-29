package edu.curso.view;

import edu.curso.control.FornecedorController;
import edu.curso.infraestructure.FornecedorImplMariaDB;
import edu.curso.model.Fornecedor;
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

//
public class UIFornecedorFX implements UI {
    private final FornecedorController controller = new FornecedorController(new FornecedorImplMariaDB());
    private Label lblTitulo = new Label("Fornecedores");

    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblCnpj = new Label("CNPJ:");
    private TextField txtCnpj = new TextField();

    private BorderPane painelPrincipal = new BorderPane();

    private Label lblEndereco = new Label("Endereco:");
    private TextField txtEndereco = new TextField();

    private Label lblEmail = new Label("Email:");
    private TextField txtEmail = new TextField();

    private Label lblTelefone = new Label("Telefone:");
    private TextField txtTelefone = new TextField();

    private Button btnNovo = new Button("NOVO");
    private Button btnAtualizar = new Button("ATUALIZAR");
    private Button bntLimpar = new Button("LIMPAR");
    private Button btnPesquisarPorNome = new Button("PESQUISAR POR NOME");
    private Button btnPesquisarCnpj = new Button("PESQUISAR POR CNPJ");

    private TableView<Fornecedor> tblFornecedores = new TableView<>();

    @Override
    public Pane render() {
        Bindings.bindBidirectional(this.txtNome.textProperty(), this.controller.nomeProperty());
        Bindings.bindBidirectional(this.txtCnpj.textProperty(), this.controller.cnpjProperty());
        Bindings.bindBidirectional(this.txtEndereco.textProperty(), this.controller.enderecoProperty());
        Bindings.bindBidirectional(this.txtEmail.textProperty(), this.controller.emailProperty());
        Bindings.bindBidirectional(this.txtTelefone.textProperty(), this.controller.telefoneProperty());

        GridPane painelTopo = new GridPane();
        painelTopo.setHgap(10);
        painelTopo.setVgap(10);

        this.lblTitulo.setStyle("-fx-font-size: 20px");
        painelTopo.add(this.lblTitulo, 0, 0);

        painelTopo.add(this.lblNome, 0, 1);
        painelTopo.add(this.txtNome, 1, 1);
        painelTopo.add(this.btnNovo, 2, 1);
        painelTopo.add(this.btnAtualizar, 3, 1);

        painelTopo.add(this.lblCnpj, 0, 2);
        painelTopo.add(this.txtCnpj, 1, 2);
        painelTopo.add(this.bntLimpar, 2, 2);

        painelTopo.add(this.lblEndereco, 0, 3);
        painelTopo.add(this.txtEndereco, 1, 3);
        painelTopo.add(this.btnPesquisarPorNome, 2, 3);

        painelTopo.add(this.lblEmail, 0, 4);
        painelTopo.add(this.txtEmail, 1, 4);
        painelTopo.add(this.btnPesquisarCnpj, 2, 4);

        painelTopo.add(this.lblTelefone, 0, 5);
        painelTopo.add(this.txtTelefone, 1, 5);

        this.btnNovo.setOnAction(e -> {
            try {
                this.controller.save();
                this.tblFornecedores.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Novo Fornecedor");
                success.setContentText("Fornecedor salvo!");
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
                this.tblFornecedores.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Alteracao Concluida");
                success.setContentText("Fornecedor teve dados alterados!");
                success.showAndWait();
            } catch (RuntimeException rex) {
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Erro de input");
                error.setContentText(rex.getMessage());
                error.showAndWait();                
            }
        });
//

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
                this.controller.findByNome();
            }

        });

        this.btnPesquisarCnpj.setOnAction(e -> {
            String cnpj = this.controller.getCnpj();

            if (cnpj == null || cnpj.isBlank() || cnpj.length() < 14) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERRO");
                alert.setContentText("CNPJ nao pode ser vazio e deve conter 14 caracteres");
                alert.showAndWait();
            } else {
                this.controller.findByCnpj();
            }

        });

        TableColumn<Fornecedor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Fornecedor, String> colCnpj = new TableColumn<>("CNPJ");
        colCnpj.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCnpj())
        );

        TableColumn<Fornecedor, String> colEndereco = new TableColumn<>("Endereco");
        colEndereco.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEndereco())
        );

        TableColumn<Fornecedor, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEmail())
        );
        //

        TableColumn<Fornecedor, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTelefone())
        );

        TableColumn<Fornecedor, Void> colAcoes = new TableColumn<>("Acoes");

        this.tblFornecedores.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> this.controller.fromEntity(novo)
        );

        this.tblFornecedores.getColumns().add(colNome);
        this.tblFornecedores.getColumns().add(colCnpj);
        this.tblFornecedores.getColumns().add(colEndereco);
        this.tblFornecedores.getColumns().add(colEmail);
        this.tblFornecedores.getColumns().add(colTelefone);

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


        this.painelPrincipal.setPadding(new Insets(15));
        this.painelPrincipal.setTop(painelTopo);
        BorderPane.setMargin(painelTopo, new Insets(15));
        this.painelPrincipal.setCenter(this.tblFornecedores);

        return this.painelPrincipal;
    }
}