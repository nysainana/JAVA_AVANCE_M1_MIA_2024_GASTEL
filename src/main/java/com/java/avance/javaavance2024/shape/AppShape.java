package com.java.avance.javaavance2024.shape;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineJoin;

public class AppShape<S extends Shape> extends AnchorPane {

    protected S content;
    protected double defaultStrokeWidth = 4;

    public AppShape(S content){
        super();
        this.content = content;
        this.getChildren().addFirst(this.content);
        this.setPrefSize(200, 200);
        this.content.setStroke(Color.BLACK);
        this.content.setStrokeWidth(defaultStrokeWidth);
        this.content.setStrokeLineJoin(StrokeLineJoin.ROUND);
        this.content.setFill(Color.TRANSPARENT);
    }

    protected void setContent(S content) {
        this.content = content;
        this.getChildren().addFirst(this.content);
    }

    public S getContent() {
        return content;
    }
}
