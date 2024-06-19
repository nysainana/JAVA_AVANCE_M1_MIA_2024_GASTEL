package com.java.avance.javaavance2024;

import com.java.avance.javaavance2024.components.ConfirmDialog;
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
        Scene scene = new Scene(MainApplication.root, 1024, 750);
        stage.setTitle("Java avancée!");
        stage.setScene(scene);
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setResizable(true);

        stage.setOnCloseRequest((event) -> {
            event.consume();
            this.closeApp(stage);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void closeApp(Stage stage){
        if(MainApplication.root.lookupAll("#dialog-close").isEmpty()) {
            ConfirmDialog dialog = new ConfirmDialog("Quiter", "Etes vous sur de vouloir quiter l'application ?");
            dialog.setId("dialog-close");
            dialog.onConfirm(evt -> {
                stage.close();
                System.exit(0);
            });
            dialog.show();
        }
    }

}