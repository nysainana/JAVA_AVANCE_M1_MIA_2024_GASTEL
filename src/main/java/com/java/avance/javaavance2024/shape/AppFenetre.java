package com.java.avance.javaavance2024.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AppFenetre extends AppShape<Rectangle>{


    public AppFenetre(){
        super(new Rectangle());
        this._init();
    }

    private void _init(){
        this.setPrefSize(defaultStrokeWidth,40);
        this.content.setWidth(defaultStrokeWidth);
        this.content.setStrokeWidth(1);
        this.content.setStroke(Color.BLACK);
        this.content.setFill(Color.WHITE);
        this.content.heightProperty().bind(this.prefHeightProperty());
        this.content.xProperty().bind(this.prefWidthProperty().subtract(defaultStrokeWidth));
    }

}
