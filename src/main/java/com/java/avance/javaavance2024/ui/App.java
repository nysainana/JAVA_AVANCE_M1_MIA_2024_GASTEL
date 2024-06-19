package com.java.avance.javaavance2024.ui;


import com.java.avance.javaavance2024.components.ResizablePane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    private StackPane paneCanvas;
    @FXML
    public StackPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResizablePane pane = new ResizablePane();
        pane.setPrefWidth(500);
        pane.setPrefHeight(500);

        paneCanvas.getChildren().add(pane);
    }

}
