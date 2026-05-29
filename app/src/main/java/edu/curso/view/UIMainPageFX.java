package edu.curso.view;

import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UIMainPageFX extends Application {

    private BorderPane painelPrincipal = new BorderPane();

    // Telas
    // private Pane uiMedicamento = new UIMedicamentoFX().render();
    private Pane uiFornecedor = new UIFornecedorFX().render();

    // Menu
    private MenuBar menuBar = new MenuBar();
    private Menu mnuArquivo = new Menu("Arquivos");
    private Menu mnuGestao = new Menu("Gestao");
    private Menu mnuAjuda = new Menu("Ajuda");

    private MenuItem mnuInicialItem = new MenuItem("Inicial");
    private MenuItem mnuSairItem = new MenuItem("Sair");

    // private MenuItem mnuMedicamentosItem = new MenuItem("Medicamentos");
    private MenuItem mnuFornecedoresItem = new MenuItem("Fornecedores");

    // Tela inicial
    private VBox layoutInicial = new VBox();
    private HBox boxBotoes = new HBox();

    private Label lblTitulo = new Label("Farmacia Popular");
    private Label lblDescricao = new Label(
        "(Clique em um dos botoes abaixo para cadastrar, remover ou atualizar informacoes)"
    );

    // private Button btnMedicamento = new Button("Medicamentos");
    private Button btnFornecedor = new Button("Fornecedores");

    @Override
    public void start(Stage primaryStage) {

        Scene scn = new Scene(this.painelPrincipal, 800, 600);

        // Menu
        this.mnuArquivo.getItems().addAll(this.mnuInicialItem, this.mnuSairItem);
        this.mnuGestao.getItems().addAll(this.mnuFornecedoresItem); 

        this.menuBar.getMenus().addAll(this.mnuArquivo, this.mnuGestao, this.mnuAjuda);
        this.painelPrincipal.setTop(this.menuBar);

        // Estilo
        this.lblTitulo.setStyle("-fx-font-size: 42px;");
        this.lblDescricao.setStyle("-fx-font-size: 12px;");

        // Botões lado a lado
        this.boxBotoes.getChildren().addAll(btnFornecedor);
        this.boxBotoes.getChildren().addAll(this.btnMedicamento, this.btnFornecedor);
        this.boxBotoes.setSpacing(20);
        this.boxBotoes.setAlignment(Pos.CENTER);

        // Layout central
        this.layoutInicial.getChildren().addAll(lblTitulo, lblDescricao, boxBotoes);
        this.layoutInicial.setSpacing(15);
        this.layoutInicial.setAlignment(Pos.CENTER);
        this.layoutInicial.setStyle("-fx-padding: 20px;");

        // Coloca no centro
        this.painelPrincipal.setCenter(this.layoutInicial);

        this.mnuInicialItem.setOnAction(e -> {
            this.painelPrincipal.setCenter(this.layoutInicial);
        });

        this.mnuSairItem.setOnAction(e -> {
            Alert msg = new Alert(AlertType.CONFIRMATION, "Sair?", ButtonType.YES, ButtonType.NO);
            msg.setTitle("Sair");

            Optional<ButtonType> result = msg.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                System.exit(0);
            }
        });

        // Ações (troca de telas)
        // this.mnuMedicamentosItem.setOnAction(e -> {
        //     this.painelPrincipal.setCenter(this.uiMedicamento);
        // });

        // this.btnMedicamento.setOnAction(e -> {
        //     this.painelPrincipal.setCenter(this.uiMedicamento);
        // });

        this.mnuFornecedoresItem.setOnAction(e -> {
            this.painelPrincipal.setCenter(this.uiFornecedor);
        });

        this.btnFornecedor.setOnAction(e -> {
            this.painelPrincipal.setCenter(this.uiFornecedor);
        });

        primaryStage.setScene(scn);
        primaryStage.setTitle("Farmacia Popular");
        primaryStage.show();
    }
}

