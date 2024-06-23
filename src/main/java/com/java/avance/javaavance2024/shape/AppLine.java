package com.java.avance.javaavance2024.shape;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

public class AppLine extends AppShape<Line>{

    public AppLine(){
        super(new Line());
        this._init();
    }

    private void _init(){
        this.content.setStartX(0);
        this.content.setStartY(0);
        this.content.endXProperty().bind(this.prefWidthProperty());
        this.content.endYProperty().bind(this.prefHeightProperty());
    }

}
