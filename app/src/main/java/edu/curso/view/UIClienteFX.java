package edu.curso.view;

import edu.curso.control.ClienteController;
import edu.curso.infraestructure.ClienteImplMariaDB;
import edu.curso.model.Cliente;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
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

import java.time.LocalDate;
import java.util.Optional;

public class UIClienteFX implements UI {
    private final ClienteController controller = new ClienteController(new ClienteImplMariaDB());
    private Label lblTitulo = new Label("Clientes");

    private Label lblNome = new Label("Nome:");
    private TextField txtNome = new TextField();

    private Label lblCpf = new Label("CPF:");
    private TextField txtCpf = new TextField();

    private BorderPane painelPrincipal = new BorderPane();

    private Label lblEndereco = new Label("Endereco:");
    private TextField txtEndereco = new TextField();

    private Label lblTelefone = new Label("Telefone:");
    private TextField txtTelefone = new TextField();

    private Label lblDataNascimento = new Label("Data de Nascimento:");
    private DatePicker dtDataNascimento = new DatePicker(LocalDate.now());

    private Label lblRpopular = new Label("Registro Popular:");
    private TextField txtRpopular = new TextField();

    private Button btnNovo = new Button("NOVO");
    private Button btnAtualizar = new Button("ATUALIZAR");
    private Button bntLimpar = new Button("LIMPAR");
    private Button btnPesquisarPorNome = new Button("PESQUISAR POR NOME");
    private Button btnPesquisarcpf = new Button("PESQUISAR POR CPF");

    private TableView<Cliente> tblClientes = new TableView<>();

    @Override
    public Pane render() {
        Bindings.bindBidirectional(this.txtNome.textProperty(), this.controller.nomeProperty());
        Bindings.bindBidirectional(this.txtCpf.textProperty(), this.controller.cpfProperty());
        Bindings.bindBidirectional(this.txtEndereco.textProperty(), this.controller.enderecoProperty());
        Bindings.bindBidirectional(this.txtRpopular.textProperty(), this.controller.rPopularProperty());
        Bindings.bindBidirectional(this.txtTelefone.textProperty(), this.controller.telefoneProperty());
        Bindings.bindBidirectional(this.dtDataNascimento.valueProperty(), this.controller.dataNascimentoProperty());
 

        GridPane painelTopo = new GridPane();
        painelTopo.setHgap(10);
        painelTopo.setVgap(10);

        this.lblTitulo.setStyle("-fx-font-size: 20px");
        painelTopo.add(this.lblTitulo, 0, 0);

        painelTopo.add(this.lblNome, 0, 1);
        painelTopo.add(this.txtNome, 1, 1);
        painelTopo.add(this.btnNovo, 2, 1);
        painelTopo.add(this.btnAtualizar, 3, 1);

        painelTopo.add(this.lblCpf, 0, 2);
        painelTopo.add(this.txtCpf, 1, 2);
        painelTopo.add(this.bntLimpar, 2, 2);

        painelTopo.add(this.lblEndereco, 0, 3);
        painelTopo.add(this.txtEndereco, 1, 3);
        painelTopo.add(this.btnPesquisarPorNome, 2, 3);

        painelTopo.add(this.lblRpopular, 0, 4);
        painelTopo.add(this.txtRpopular, 1, 4);
        painelTopo.add(this.btnPesquisarcpf, 2, 4);

        painelTopo.add(this.lblTelefone, 0, 5);
        painelTopo.add(this.txtTelefone, 1, 5);

        this.btnNovo.setOnAction(e -> {
            try {
                this.controller.save();
                this.tblClientes.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Novo Cliente");
                success.setContentText("Cliente salvo!");
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
                this.tblClientes.getSelectionModel().clearSelection();
                Alert success = new Alert(AlertType.INFORMATION);
                success.setTitle("Alteracao Concluida");
                success.setContentText("Cliente teve dados alterados!");
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
                this.controller.searchByName();
            }

        });

        this.btnPesquisarcpf.setOnAction(e -> {
            String cpf = this.controller.getCpf();

            if (cpf == null || cpf.isBlank() || cpf.length() < 11) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("ERRO");
                alert.setContentText("CPF nao pode ser vazio e deve conter 11 caracteres");
                alert.showAndWait();
            } else {
                this.controller.searchByCpf();
            }

        });

        TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome())
        );

        TableColumn<Cliente, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getCpf())
        );

        TableColumn<Cliente, String> colEndereco = new TableColumn<>("Endereco");
        colEndereco.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getEndereco())
        );

        TableColumn<Cliente, String> colRpopular = new TableColumn<>("Registro Popular");
        colRpopular.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getRPopular())
        );
        //

        TableColumn<Cliente, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTelefone())
        );

        TableColumn<Cliente, String> colDataNascimento = new TableColumn<>("Data de Nascimento");
        colTelefone.setCellValueFactory(
            itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataNascimento().toString())
        );



        TableColumn<Cliente, Void> colAcoes = new TableColumn<>("Acoes");

        this.tblClientes.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> this.controller.fromEntity(novo)
        );

        this.tblClientes.getColumns().add(colNome);
        this.tblClientes.getColumns().add(colCpf);
        this.tblClientes.getColumns().add(colEndereco);
        this.tblClientes.getColumns().add(colRpopular);
        this.tblClientes.getColumns().add(colTelefone);

        this.tblClientes.setItems(this.controller.getLista());

        Callback<TableColumn<Cliente, Void>, TableCell<Cliente, Void>> 
            callback = new Callback<>(){ 
                public TableCell<Cliente, Void> call(TableColumn<Cliente, Void> column) { 
                    return new TableCell<Cliente, Void>(){
                        Button btnApagar = new Button("Apagar");

                        {
                            btnApagar.setOnAction(e -> {
                                Alert alert = new Alert(AlertType.CONFIRMATION, 
                                    "Apagar este Cliente ?", ButtonType.YES, ButtonType.NO);
                                alert.setTitle("Confirma Deleção");

                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.isPresent() && result.get() == ButtonType.YES) {
                                    UIClienteFX.this.controller.delete( getIndex() ) ;
                                    tblClientes.getSelectionModel().clearSelection();
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

        this.tblClientes.getColumns().add(colAcoes);

        colAcoes.setCellFactory( callback );


        this.painelPrincipal.setPadding(new Insets(15));
        this.painelPrincipal.setTop(painelTopo);
        BorderPane.setMargin(painelTopo, new Insets(15));
        this.painelPrincipal.setCenter(this.tblClientes);

        return this.painelPrincipal;
    }
}
