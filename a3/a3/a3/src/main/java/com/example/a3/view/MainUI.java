package com.example.a3.view;

import com.example.a3.controller.DrawingController;
import com.example.a3.imodel.InteractionModel;
import com.example.a3.model.DrawingModel;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainUI extends StackPane implements InteractionModelListener, DrawingModelListener{
    private DrawingView drawingView;
    private MiniDrawingView miniDrawingView;
    private ShapeToolbar shapeToolbar;
    private ColourToolbar colourToolbar;
    private InteractionModel iModel;
    private DrawingModel model;

    public MainUI() {
        BorderPane root = new BorderPane();
        drawingView = new DrawingView(500,500, Color.GAINSBORO);
        miniDrawingView = new MiniDrawingView(100,100, Color.DARKGREY);
        shapeToolbar = new ShapeToolbar();
        colourToolbar = new ColourToolbar();

        StackPane drawing = new StackPane();
        StackPane.setAlignment(miniDrawingView.myCanvas, Pos.TOP_LEFT);
        drawing.getChildren().addAll(drawingView.myCanvas, miniDrawingView.myCanvas);

        drawing.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.drawingView.myCanvas.setWidth((double) newVal);
        });
        drawing.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.drawingView.myCanvas.setHeight((double) newVal);
        });

        root.setLeft(shapeToolbar);
        root.setCenter(drawing);
        root.setRight(colourToolbar);

        this.getChildren().add(root);
    }

    public void setModel(DrawingModel model) {
        this.model = model;
        this.drawingView.setModel(this.model);
        this.miniDrawingView.setModel(this.model);
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
        shapeToolbar.setInteractionModel(this.iModel);
        colourToolbar.setInteractionModel(this.iModel);
        this.drawingView.setInteractionModel(this.iModel);
        this.miniDrawingView.setInteractionModel(this.iModel);
    }

    @Override
    public void modelChanged() {
        this.drawingView.modelChanged();
        this.miniDrawingView.modelChanged();
    }

    @Override
    public void iModelChanged() {
        this.shapeToolbar.iModelChanged();
        this.colourToolbar.iModelChanged();
        this.drawingView.iModelChanged();
        this.miniDrawingView.iModelChanged();

    }

    public void setController(DrawingController controller) {
        this.drawingView.setController(controller);
        this.miniDrawingView.setController(controller);

        this.setOnKeyPressed(event-> {
            this.drawingView.setOnKeyPressed(event);
        });

        this.shapeToolbar.getShapeGroup().selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                ToggleButton b = (ToggleButton) newVal;
                controller.handleNewSelectedShape(b);
            }
        });

        this.colourToolbar.getColourGroup().selectedToggleProperty().addListener((obs,oldVal,newVal)-> {
                if (newVal != null) {
                    ToggleButton newButton = (ToggleButton) newVal;
                    String colorName = newButton.getText().toUpperCase();
                    Color c = Color.web(colorName);
                    controller.handleNewSelectedColour(c);
                }
            });
    }

}
