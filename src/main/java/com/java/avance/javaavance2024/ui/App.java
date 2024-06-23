package com.java.avance.javaavance2024.ui;


import com.java.avance.javaavance2024.MainApplication;
import com.java.avance.javaavance2024.components.ConfirmDialog;
import com.java.avance.javaavance2024.components.ResizableMovablePane;
import com.java.avance.javaavance2024.shape.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;

import java.net.URL;
import java.util.ResourceBundle;

public class App implements Initializable {

    @FXML
    private StackPane root;

    @FXML
    private ScrollPane scroolControl;

    @FXML
    private SplitPane splitContent;

    @FXML
    private VBox boxMenu;

    @FXML
    private JFXButton buttonCircle;

    @FXML
    private JFXButton buttonLine;

    @FXML
    private JFXButton buttonDelete;

    @FXML
    private JFXButton buttonClose;

    @FXML
    private JFXButton buttonRectangle;

    @FXML
    private JFXButton buttonTriangle;

    @FXML
    private JFXCheckBox checkContour;

    @FXML
    private JFXCheckBox checkRemplissage;

    @FXML
    private ColorPicker colorContour;

    @FXML
    private ColorPicker colorRemplissage;

    @FXML
    private Spinner<Integer> tailleContour;

    @FXML
    private Spinner<Double> fieldHeight;

    @FXML
    private Spinner<Double> fieldPosX;

    @FXML
    private Spinner<Double> fieldPosY;

    @FXML
    private Spinner<Double> fieldWidth;

    @FXML
    private StackPane paneCanvas;

    @FXML
    private MenuItem menuDelete;


    private final ObjectProperty<ResizableMovablePane> canvas = new SimpleObjectProperty<>();
    private final ObjectProperty<JFXButton> selectedShapeMenu = new SimpleObjectProperty<>();
    private final ObjectProperty<AppShape> selectedShape = new SimpleObjectProperty<>();

    private ResizableMovablePane resizerMover;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colorContour.getParent().disableProperty().bind(checkContour.selectedProperty().not());
        tailleContour.getParent().disableProperty().bind(checkContour.selectedProperty().not());
        colorRemplissage.getParent().disableProperty().bind(checkRemplissage.selectedProperty().not());

        fieldPosX.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0));
        fieldPosY.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-Double.MAX_VALUE, Double.MAX_VALUE, 0));
        fieldWidth.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0));
        fieldHeight.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0));
        tailleContour.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 3));

        fieldPosX.setEditable(true);
        fieldPosY.setEditable(true);
        fieldWidth.setEditable(true);
        fieldHeight.setEditable(true);
        tailleContour.setEditable(true);
        colorContour.setValue(Color.BLACK);

        fieldPosX.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(this.resizerMover != null) this.resizerMover.setLayoutX(newValue);
        });
        fieldPosY.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(this.resizerMover != null) this.resizerMover.setLayoutY(newValue);
        });
        fieldWidth.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(this.resizerMover != null) this.resizerMover.setPrefWidth(newValue);
        });
        fieldHeight.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(this.resizerMover != null) this.resizerMover.setPrefHeight(newValue);
        });

        checkContour.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(selectedShape.get() != null) selectedShape.get().getContent().setStroke(newValue ? colorContour.getValue() : Color.TRANSPARENT);
        });
        checkRemplissage.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            if(selectedShape.get() != null) selectedShape.get().getContent().setFill(newValue ? colorRemplissage.getValue() : Color.TRANSPARENT);
        });

        colorContour.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(selectedShape.get() != null) selectedShape.get().getContent().setStroke(newValue);
        });
        colorRemplissage.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(selectedShape.get() != null) selectedShape.get().getContent().setFill(newValue);
        });
        tailleContour.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(selectedShape.get() != null) selectedShape.get().getContent().setStrokeWidth(newValue);
        });

        buttonDelete.setOnAction(event -> {
            this._deleteForm();
        });
        menuDelete.disableProperty().bind(selectedShape.isNull());
        menuDelete.setOnAction(event -> {
            this._deleteForm();
        });

        buttonClose.setOnAction(event -> {
            if(this.selectedShape.get() != null) this.selectedShape.setValue(null);
        });

        selectedShapeMenu.addListener((observableValue, oldValue, newValue) -> {
            if(oldValue != null) oldValue.getStyleClass().remove("selected-shape-menu");
            if(newValue != null) {
                newValue.getStyleClass().add("selected-shape-menu");
                this.selectedShape.setValue(null);
            }
            if(canvas.get() != null) canvas.get().setCursor(newValue == null ? Cursor.DEFAULT : Cursor.CROSSHAIR);
        });

        this.resizerMover = new ResizableMovablePane();
        this.resizerMover.setStyle("-fx-border-width: 1; -fx-border-color: #666666; -fx-border-style: dashed; -fx-background-color: rgba(0, 0, 0, 0.1)");
        this.resizerMover.prefHeightProperty().addListener((ov, oldV, newV) -> {
            this.fieldHeight.getValueFactory().setValue(newV.doubleValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setPrefHeight(newV.doubleValue());
        });
        this.resizerMover.prefWidthProperty().addListener((ov, oldV, newV) -> {
            this.fieldWidth.getValueFactory().setValue(newV.doubleValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setPrefWidth(newV.doubleValue());
        });
        this.resizerMover.layoutXProperty().addListener((ov, oldV, newV) -> {
            this.fieldPosX.getValueFactory().setValue(newV.doubleValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setLayoutX(newV.doubleValue());
        });
        this.resizerMover.layoutYProperty().addListener((ov, oldV, newV) -> {
            this.fieldPosY.getValueFactory().setValue(newV.doubleValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setLayoutY(newV.doubleValue());
        });
        this.resizerMover.setOnKeyPressed(keyEvent -> {
            System.out.println(keyEvent.getCode());
        });

        canvas.addListener((observableValue, oldValue, newValue) -> {
            if(newValue == null) return;

            newValue.setPrefSize(500, 500);
            newValue.setMinimumSize(100, 100);
            newValue.getStyleClass().add("canvas");
            newValue.movableProperty().set(false);

            Rectangle rectangle = new Rectangle();
            rectangle.widthProperty().bind(newValue.prefWidthProperty());
            rectangle.heightProperty().bind(newValue.prefHeightProperty());
            newValue.setClip(rectangle);

            newValue.setOnMouseClicked(event -> {
                if(this.selectedShapeMenu.get() != null){
                    AppShape shape = null;
                    double w = 200;
                    double h = 200;
                    double x = event.getX();
                    double y = event.getY();
                    Color stroke = checkContour.isSelected() ? colorContour.getValue() : Color.TRANSPARENT;
                    int strokeWidth = tailleContour.getValue();
                    Color fill = checkRemplissage.isSelected() ? colorRemplissage.getValue() : Color.TRANSPARENT;

                    if(this.selectedShapeMenu.get() == this.buttonLine)
                        shape = new AppLine();

                    if(this.selectedShapeMenu.get() == this.buttonRectangle)
                        shape = new AppRectangle();

                    if(this.selectedShapeMenu.get() == this.buttonCircle)
                        shape = new AppEllipse();

                    if(this.selectedShapeMenu.get() == this.buttonTriangle)
                        shape = new AppTriangle();

                    if (shape != null){
                        shape.setPrefSize(w, h);
                        shape.setLayoutX(x);
                        shape.setLayoutY(y);
                        shape.getContent().setStroke(stroke);
                        shape.getContent().setStrokeWidth(strokeWidth);
                        shape.getContent().setFill(fill);
                        shape.getContent().setStrokeLineJoin(StrokeLineJoin.ROUND);
                        newValue.getChildren().add(shape);
                        this.selectedShape.setValue(shape);
                    }
                    this.selectedShapeMenu.setValue(null);
                }
            });
            paneCanvas.getChildren().add(newValue);
        });
        canvas.setValue(new ResizableMovablePane());


        selectedShape.addListener((observableValue, oldValue, newValue) -> {
            if(newValue != null) {
                newValue.setOnMouseClicked(evt -> this.selectedShape.setValue(newValue));
                if(!splitContent.getItems().contains(scroolControl)) splitContent.getItems().addLast(scroolControl);
                colorContour.setValue((Color) newValue.getContent().getStroke());
                checkContour.setSelected(!newValue.getContent().getStroke().equals(Color.TRANSPARENT));
                colorRemplissage.setValue((Color) newValue.getContent().getFill());
                checkRemplissage.setSelected(!newValue.getContent().getFill().equals(Color.TRANSPARENT));
                tailleContour.getValueFactory().setValue((int) newValue.getContent().getStrokeWidth());
            }
            else {
                splitContent.getItems().remove(scroolControl);
                if (this.canvas.get() != null) this.canvas.get().getChildren().remove(this.resizerMover);
            }

            if(newValue != null && this.resizerMover != null && this.canvas.get() != null) {
                this.resizerMover.setLayoutX(newValue.getLayoutX());
                this.resizerMover.setLayoutY(newValue.getLayoutY());
                this.resizerMover.setPrefSize(newValue.getPrefWidth(), newValue.getPrefHeight());

                this.canvas.get().getChildren().remove(this.resizerMover);
                this.canvas.get().getChildren().add(this.canvas.get().getChildren().size(), this.resizerMover);
            }
        });
        splitContent.getItems().remove(scroolControl);

    }

    @FXML
    void handleChoiceAction(ActionEvent event) {
        selectedShapeMenu.setValue((JFXButton) event.getSource());
    }

    @FXML
    public void quiter(ActionEvent event){
        if(MainApplication.root.lookupAll("#dialog-close").isEmpty()) {
            ConfirmDialog dialog = new ConfirmDialog("Quiter", "Etes vous sur de vouloir quiter l'application ?");
            dialog.setId("dialog-close");
            dialog.onConfirm(evt -> {
                System.exit(0);
            });
            dialog.show();
        }
    }

    /**************************** FONCTION UTILS *******************************/

    private void _deleteForm(){
        if(this.canvas.get() != null && this.selectedShape.get() != null){
            this.canvas.get().getChildren().remove(this.selectedShape.get());
            this.selectedShape.setValue(null);
        }
    }

}
