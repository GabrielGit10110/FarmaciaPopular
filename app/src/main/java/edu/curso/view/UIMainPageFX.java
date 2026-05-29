package edu.curso.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UIMainPageFX extends Application {
    private BorderPane painelPrincipal = new BorderPane();
    private Pane uiMedicamento = new UIMedicamentoFX().render();

    private MenuBar menuBar = new MenuBar();

    private Menu mnuArquivo = new Menu("Arquivos");
    private Menu mnuGestao = new Menu("Gestao");
    private Menu mnuAjuda = new Menu("Ajuda");

    private MenuItem mnuMedicamentosItem = new MenuItem("Medicamentos");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scn = new Scene(this.painelPrincipal, 800, 600);

        this.mnuGestao.getItems().addAll(this.mnuMedicamentosItem);
        this.menuBar.getMenus().addAll(this.mnuArquivo, this.mnuGestao, this.mnuAjuda);

        this.painelPrincipal.setTop(this.menuBar);

        mnuMedicamentosItem.setOnAction(e -> {
            this.painelPrincipal.setCenter(uiMedicamento);
        });

        primaryStage.setScene(scn);
        primaryStage.show();
    }

}
