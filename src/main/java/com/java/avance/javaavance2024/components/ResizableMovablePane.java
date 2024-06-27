package com.java.avance.javaavance2024.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

import java.awt.*;

public class ResizableMovablePane extends AnchorPane {

    private double minWidth = 0;
    private double minHeight = 0;

    private double xm, ym;

    private final AnchorPane canvas;

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
            resizer.setCenterX(Math.max(event.getX(), this.minWidth));
            resizer.setCenterY(Math.max(event.getY(), this.minHeight));
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
            this.setLayoutX(MouseInfo.getPointerInfo().getLocation().x - xm);
            this.setLayoutY(MouseInfo.getPointerInfo().getLocation().y - ym);
        });

        canvas = new AnchorPane();
        canvas.prefWidthProperty().bind(this.prefWidthProperty());
        canvas.prefHeightProperty().bind(this.prefHeightProperty());

        this.getChildren().add(canvas);
        this.getChildren().add(mouver);
        this.getChildren().add(resizer);
    }

    public void add(Node node){
        this.canvas.getChildren().add(node);
    }

    public void remove(Node node){
        this.canvas.getChildren().remove(node);
    }

    public void setClipNode(Node node){
        this.canvas.setClip(node);
    }

    public void setMinimumSize(double w, double h) {
        this.minWidth = w >= 0 ? w : 0;
        this.minHeight = h >= 0 ? h : 0;
    }

    public BooleanProperty resizableProperty() {
        return resizable;
    }

    public BooleanProperty movableProperty() {
        return movable;
    }

    public void setResizable(boolean resizable){
        this.resizable.setValue(resizable);
    }

    public void setMovable(boolean movable){
        this.movable.setValue(movable);
    }
}
