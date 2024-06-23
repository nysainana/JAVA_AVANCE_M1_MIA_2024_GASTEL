package com.java.avance.javaavance2024.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

public class AppEllipse extends AppShape<Ellipse>{

    public AppEllipse(){
        super(new Ellipse());
        this._init();
    }

    private void _init(){
        this.content.radiusXProperty().bind(this.prefWidthProperty().divide(2));
        this.content.radiusYProperty().bind(this.prefHeightProperty().divide(2));
        this.content.centerYProperty().bind(this.prefHeightProperty().divide(2));
        this.content.centerXProperty().bind(this.prefWidthProperty().divide(2));
    }

}
