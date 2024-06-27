package com.java.avance.javaavance2024.ui;


import com.java.avance.javaavance2024.MainApplication;
import com.java.avance.javaavance2024.components.ConfirmDialog;
import com.java.avance.javaavance2024.components.ResizableMovablePane;
import com.java.avance.javaavance2024.shape.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineJoin;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
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
    private JFXButton buttonDoor;

    @FXML
    private JFXButton buttonWindow;

    @FXML
    private Spinner<Integer> fieldHeight;

    @FXML
    private Spinner<Integer> fieldPosX;

    @FXML
    private Spinner<Integer> fieldPosY;

    @FXML
    private Spinner<Integer> fieldWidth;

    @FXML
    private Spinner<Integer> fieldRotation;

    @FXML
    private StackPane paneCanvas;

    @FXML
    private MenuItem menuDelete;

    @FXML
    private MenuItem menuArreter;

    @FXML
    private MenuItem menuDown;

    @FXML
    private MenuItem menuDownHeight;

    @FXML
    private MenuItem menuLeft;

    @FXML
    private MenuItem menuLeftWidth;

    @FXML
    private MenuItem menuRight;

    @FXML
    private MenuItem menuRightWidth;

    @FXML
    private MenuItem menuUp;

    @FXML
    private MenuItem menuUpHeight;

    @FXML
    private MenuItem menuNouveau;


    private final ObjectProperty<ResizableMovablePane> canvas = new SimpleObjectProperty<>();
    private final ObjectProperty<JFXButton> selectedShapeMenu = new SimpleObjectProperty<>();
    private final ObjectProperty<AppShape> selectedShape = new SimpleObjectProperty<>();

    private ResizableMovablePane resizerMover;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fieldPosX.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-Integer.MAX_VALUE, Integer.MAX_VALUE, 0));
        fieldPosY.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-Integer.MAX_VALUE, Integer.MAX_VALUE, 0));
        fieldWidth.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
        fieldHeight.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, 0));
        fieldRotation.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 360, 0));

        fieldPosX.setEditable(true);
        fieldPosY.setEditable(true);
        fieldWidth.setEditable(true);
        fieldHeight.setEditable(true);
        fieldRotation.setEditable(true);

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
        fieldRotation.getValueFactory().valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if(this.resizerMover != null) this.resizerMover.setRotate(newValue);
        });

        menuDelete.disableProperty().bind(selectedShape.isNull());
        menuUp.disableProperty().bind(selectedShape.isNull());
        menuUpHeight.disableProperty().bind(selectedShape.isNull());
        menuDown.disableProperty().bind(selectedShape.isNull());
        menuDownHeight.disableProperty().bind(selectedShape.isNull());
        menuLeft.disableProperty().bind(selectedShape.isNull());
        menuLeftWidth.disableProperty().bind(selectedShape.isNull());
        menuRight.disableProperty().bind(selectedShape.isNull());
        menuRightWidth.disableProperty().bind(selectedShape.isNull());

        buttonDelete.setOnAction(event -> this._deleteForm());
        menuDelete.setOnAction(event -> this._deleteForm());
        menuArreter.setOnAction(event -> selectedShape.setValue(null));
        menuUp.setOnAction(event -> this.resizerMover.setLayoutY(this.resizerMover.getLayoutY() - 1));
        menuDown.setOnAction(event -> this.resizerMover.setLayoutY(this.resizerMover.getLayoutY() + 1));
        menuLeft.setOnAction(event -> this.resizerMover.setLayoutX(this.resizerMover.getLayoutX() - 1));
        menuRight.setOnAction(event -> this.resizerMover.setLayoutX(this.resizerMover.getLayoutX() + 1));
        menuUpHeight.setOnAction(event -> this.resizerMover.setPrefHeight(this.resizerMover.getPrefHeight() - 1));
        menuDownHeight.setOnAction(event -> this.resizerMover.setPrefHeight(this.resizerMover.getPrefHeight() + 1));
        menuLeftWidth.setOnAction(event -> this.resizerMover.setPrefWidth(this.resizerMover.getPrefWidth() - 1));
        menuRightWidth.setOnAction(event -> this.resizerMover.setPrefWidth(this.resizerMover.getPrefWidth() + 1));
        menuNouveau.setOnAction(event -> {
            if(MainApplication.root.lookupAll("#dialog-new").isEmpty()) {
                ConfirmDialog dialog = new ConfirmDialog("Nouvel", "Etes vous sur de vouloir crée un nouvel espace de travail ?");
                dialog.setId("dialog-new");
                dialog.setOkText("Crée");
                dialog.onConfirm(evt -> {
                    canvas.setValue(new ResizableMovablePane());
                });
                dialog.show();
            }
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
            this.fieldHeight.getValueFactory().setValue(newV.intValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setPrefHeight(newV.doubleValue());
        });
        this.resizerMover.prefWidthProperty().addListener((ov, oldV, newV) -> {
            this.fieldWidth.getValueFactory().setValue(newV.intValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setPrefWidth(newV.doubleValue());
        });
        this.resizerMover.layoutXProperty().addListener((ov, oldV, newV) -> {
            this.fieldPosX.getValueFactory().setValue(newV.intValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setLayoutX(newV.doubleValue());
        });
        this.resizerMover.layoutYProperty().addListener((ov, oldV, newV) -> {
            this.fieldPosY.getValueFactory().setValue(newV.intValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setLayoutY(newV.doubleValue());
        });
        this.resizerMover.rotateProperty().addListener((ov, oldV, newV) -> {
            this.fieldRotation.getValueFactory().setValue(newV.intValue());
            if(this.selectedShape.get() != null) this.selectedShape.get().setRotate(newV.doubleValue());
        });

        canvas.addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) paneCanvas.getChildren().remove(oldValue);
            if(newValue == null) return;

            selectedShape.setValue(null);

            newValue.setPrefSize(800, 500);
            newValue.setMinimumSize(100, 100);
            newValue.getStyleClass().add("canvas");
            newValue.setMovable(false);

            Rectangle rectangle = new Rectangle();
            rectangle.widthProperty().bind(newValue.prefWidthProperty());
            rectangle.heightProperty().bind(newValue.prefHeightProperty());
            newValue.setClipNode(rectangle);

            newValue.setOnMouseClicked(event -> {
                if(this.selectedShapeMenu.get() != null){
                    AppShape shape = null;
                    double x = event.getX();
                    double y = event.getY();

                    if(this.selectedShapeMenu.get() == this.buttonLine)
                        shape = new AppLine();

                    if(this.selectedShapeMenu.get() == this.buttonRectangle)
                        shape = new AppRectangle();

                    if(this.selectedShapeMenu.get() == this.buttonCircle)
                        shape = new AppEllipse();

                    if(this.selectedShapeMenu.get() == this.buttonTriangle)
                        shape = new AppTriangle();

                    if(this.selectedShapeMenu.get() == this.buttonDoor)
                        shape = new AppPorte();

                    if(this.selectedShapeMenu.get() == this.buttonWindow)
                        shape = new AppFenetre();

                    if (shape != null){
                        shape.setLayoutX(x);
                        shape.setLayoutY(y);
                        newValue.add(shape);
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
            }
            else {
                splitContent.getItems().remove(scroolControl);
                if (this.canvas.get() != null) this.canvas.get().getChildren().remove(this.resizerMover);
            }

            if(newValue != null && this.resizerMover != null && this.canvas.get() != null) {
                this.resizerMover.setLayoutX(newValue.getLayoutX());
                this.resizerMover.setLayoutY(newValue.getLayoutY());
                this.resizerMover.setPrefSize(newValue.getPrefWidth(), newValue.getPrefHeight());
                this.resizerMover.setRotate(newValue.getRotate());

                this.canvas.get().getChildren().remove(this.resizerMover);
                this.canvas.get().getChildren().add(this.canvas.get().getChildren().size(), this.resizerMover);
            }
        });
        splitContent.getItems().remove(scroolControl);

    }

    @FXML
    void handleChoiceAction(ActionEvent event) {
        JFXButton source = (JFXButton) event.getSource();
        if(selectedShapeMenu.get() != null && selectedShapeMenu.get().equals(source)) selectedShapeMenu.setValue(null);
        else selectedShapeMenu.setValue(source);
    }

    @FXML
    public void quiter(ActionEvent event){
        if(MainApplication.root.lookupAll("#dialog-close").isEmpty()) {
            ConfirmDialog dialog = new ConfirmDialog("Quiter", "Etes vous sur de vouloir quiter l'application ?");
            dialog.setId("dialog-close");
            dialog.setOkText("Quiter");
            dialog.onConfirm(evt -> {
                System.exit(0);
            });
            dialog.show();
        }
    }

    @FXML
    void handleSaveAs(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enrégistrer sous");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));

        File f = fileChooser.showSaveDialog(root.getScene().getWindow());

        if(f != null){
            try {
                this.selectedShape.setValue(null);
                this.canvas.get().resizableProperty().set(false);

                WritableImage writableImage = new WritableImage((int) canvas.get().getWidth(), (int) canvas.get().getHeight());
                SnapshotParameters sp = new SnapshotParameters();
                sp.setViewport( new Rectangle2D( canvas.get().getLayoutX(), canvas.get().getLayoutY(), canvas.get().getWidth(), canvas.get().getHeight()));
                canvas.get().snapshot( sp, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", f);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

            this.canvas.get().resizableProperty().set(true);
        }
    }

    /**************************** FONCTION UTILS *******************************/

    private void _deleteForm(){
        if(this.canvas.get() != null && this.selectedShape.get() != null){
            this.canvas.get().remove(this.selectedShape.get());
            this.selectedShape.setValue(null);
        }
    }

}
