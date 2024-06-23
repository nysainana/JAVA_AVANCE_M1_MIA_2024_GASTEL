package com.java.avance.javaavance2024.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;

public class ResizableMovablePane extends AnchorPane {

    private double minWidth = 0;
    private double minHeight = 0;

    private double xm, ym;

    private final DoubleProperty maxBoundX = new SimpleDoubleProperty(-1);
    private final DoubleProperty maxBoundY = new SimpleDoubleProperty(-1);
    private final DoubleProperty minBoundX = new SimpleDoubleProperty(-1);
    private final DoubleProperty minBoundY = new SimpleDoubleProperty(-1);

    private final BooleanProperty movable = new SimpleBooleanProperty(true);
    private final BooleanProperty resizable = new SimpleBooleanProperty(true);

    public ResizableMovablePane(){
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
        resizer.visibleProperty().bind(resizable);

        resizer.setOnMouseDragged((event) -> {
            double centerX = Math.max(event.getX(), this.minWidth);
            double centerY = Math.max(event.getY(), this.minHeight);

            if(maxBoundX.get() > 0) centerX = Math.min(centerX, maxBoundX.get() - getLayoutX());
            if(maxBoundY.get() > 0) centerY = Math.min(centerY, maxBoundY.get() - getLayoutY());

            resizer.setCenterX(centerX);
            resizer.setCenterY(centerY);
        });

        Pane mouver = new Pane();
        mouver.setCursor(Cursor.MOVE);
        mouver.prefWidthProperty().bind(this.prefWidthProperty());
        mouver.prefHeightProperty().bind(this.prefHeightProperty());
        mouver.visibleProperty().bind(movable);
        mouver.setOnMousePressed(event -> {
            xm = MouseInfo.getPointerInfo().getLocation().x - this.getLayoutX();
            ym = MouseInfo.getPointerInfo().getLocation().y - this.getLayoutY();
        });
        mouver.setOnMouseDragged(event -> {
            double x = MouseInfo.getPointerInfo().getLocation().x - xm;
            double y = MouseInfo.getPointerInfo().getLocation().y - ym;

            double X = x;
            double Y = y;

            if (minBoundX.get() >= 0) X = Math.max(X, minBoundX.get());
            if (minBoundY.get() >= 0) Y = Math.max(Y, minBoundY.get());

            if (maxBoundX.get() > 0) X = X + this.getPrefWidth() > maxBoundX.get() ? maxBoundX.get() - this.getPrefWidth() : X;
            if (maxBoundX.get() > 0) Y = Y + this.getPrefHeight() > maxBoundY.get() ? maxBoundY.get() - this.getPrefHeight() : Y;

            this.setLayoutX(X);
            this.setLayoutY(Y);
        });

        this.getChildren().add(mouver);
        this.getChildren().add(resizer);
    }

    public void setMinimumSize(double w, double h) {
        this.minWidth = w >= 0 ? w : 0;
        this.minHeight = h >= 0 ? h : 0;
    }

    public DoubleProperty maxBoundXProperty() {
        return maxBoundX;
    }

    public DoubleProperty maxBoundYProperty() {
        return maxBoundY;
    }

    public DoubleProperty minBoundXProperty() {
        return minBoundX;
    }

    public DoubleProperty minBoundYProperty() {
        return minBoundY;
    }

    public BooleanProperty resizableProperty() {
        return resizable;
    }

    public BooleanProperty movableProperty() {
        return movable;
    }
}
