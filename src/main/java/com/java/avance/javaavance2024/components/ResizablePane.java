package com.java.avance.javaavance2024.components;

import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class ResizablePane extends AnchorPane {

    private final double minWidth = 50;
    private final double minHeight = 50;

    public ResizablePane(){
        this.getStyleClass().add("canvas");
        this.setMinWidth(50);
        this.setMinHeight(50);
        this.maxWidthProperty().bind(this.prefWidthProperty());
        this.maxHeightProperty().bind(this.prefHeightProperty());
        this.minWidthProperty().bind(this.prefWidthProperty());
        this.minHeightProperty().bind(this.prefHeightProperty());

        Circle resizer = new Circle();
        resizer.setRadius(5);
        resizer.setStyle("-fx-fill: -app-white; -fx-stroke: -app-bg-color");
        resizer.setCursor(Cursor.SE_RESIZE);
        resizer.centerXProperty().bindBidirectional(this.prefWidthProperty());
        resizer.centerYProperty().bindBidirectional(this.prefHeightProperty());

        resizer.setOnMouseDragged((event) -> {
            resizer.setCenterX(Math.max(event.getX(), this.minWidth));
            resizer.setCenterY(Math.max(event.getY(), this.minHeight));
        });

        this.getChildren().add(resizer);
    }

}
