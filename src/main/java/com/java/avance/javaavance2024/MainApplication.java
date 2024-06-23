package com.java.avance.javaavance2024;

import com.java.avance.javaavance2024.components.ConfirmDialog;
import com.java.avance.javaavance2024.ui.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static StackPane root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/java/avance/javaavance2024/ui/app.fxml"));
        MainApplication.root = fxmlLoader.load();
        App app = fxmlLoader.getController();
        Scene scene = new Scene(MainApplication.root, 1024, 750);
        stage.setTitle("Java avancée!");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setResizable(true);

        stage.setOnCloseRequest((event) -> {
            event.consume();
            app.quiter(null);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}