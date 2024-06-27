package com.java.avance.javaavance2024.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class AppPorte extends AppShape<Arc>{

    public AppPorte(){
        super(new Arc());
        this._init();
    }

    private void _init(){
        this.setPrefSize(40,40);
        this.content.setCenterY(2);
        this.content.centerXProperty().bind(this.prefWidthProperty().subtract(2));
        this.content.radiusXProperty().bind(this.prefWidthProperty().subtract(4));
        this.content.radiusYProperty().bind(this.prefHeightProperty().subtract(4));
        this.content.setStartAngle(180);
        this.content.setLength(90);
        this.content.setType(ArcType.ROUND);
        this.content.setStrokeWidth(1);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(defaultStrokeWidth);
        rectangle.setStrokeWidth(1);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        rectangle.heightProperty().bind(this.prefHeightProperty());
        rectangle.xProperty().bind(this.prefWidthProperty().subtract(defaultStrokeWidth));
        this.getChildren().add(rectangle);
    }

}
