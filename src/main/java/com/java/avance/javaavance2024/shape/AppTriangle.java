package com.java.avance.javaavance2024.shape;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AppTriangle extends AppShape<Polygon>{

    public AppTriangle(){
        super(new Polygon());
        this._init();
    }

    private void _init(){
        this._updatePoint();
        this.prefWidthProperty().addListener((observableValue, oldValue, newValue) -> this._updatePoint());
        this.prefHeightProperty().addListener((observableValue, oldValue, newValue) -> this._updatePoint());
    }

    private void _updatePoint(){
        this.content.getPoints().setAll(getPrefWidth()/2, 0d, getPrefWidth(), getPrefHeight(), 0d, getPrefHeight());
    }

}
