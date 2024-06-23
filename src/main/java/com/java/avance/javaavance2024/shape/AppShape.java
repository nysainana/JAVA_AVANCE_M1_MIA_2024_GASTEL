package com.java.avance.javaavance2024.shape;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;

public class AppShape<S extends Shape> extends AnchorPane {

    protected S content;

    public AppShape(S content){
        super();
        this.content = content;
        this.getChildren().addFirst(this.content);
    }

    protected void setContent(S content) {
        this.content = content;
        this.getChildren().addFirst(this.content);
    }

    public S getContent() {
        return content;
    }
}
