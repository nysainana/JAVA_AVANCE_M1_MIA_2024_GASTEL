package com.java.avance.javaavance2024.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AppRectangle extends AppShape<Rectangle>{

    public AppRectangle(){
        super(new Rectangle());
        this._init();
    }

    private void _init(){
        this.content.widthProperty().bind(this.prefWidthProperty());
        this.content.heightProperty().bind(this.prefHeightProperty());
    }

}
