package edu.curso.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CLIFuncionarioUI implements UI {
    private BorderPane painelPrincipal = new BorderPane();


    @Override
    public Pane render() {
        return this.painelPrincipal;
    }
}