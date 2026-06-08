package edu.curso.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UIFuncionarioFX implements UI {
    private BorderPane painelPrincipal = new BorderPane();


    @Override
    public Pane render() {
        return this.painelPrincipal;
    }
}