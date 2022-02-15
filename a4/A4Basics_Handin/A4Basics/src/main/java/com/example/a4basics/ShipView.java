package com.example.a4basics;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class ShipView extends StackPane implements ShipModelSubscriber {
    Canvas myCanvas;
    GraphicsContext gc;
    ShipModel model;
    InteractionModel iModel;
    Slider slider;

    public ShipView() {
        myCanvas = new Canvas(1000,700);
        gc = myCanvas.getGraphicsContext2D();
        this.getChildren().add(myCanvas);
        this.setStyle("-fx-background-color: black");

        slider = new Slider(-360, 360, 0);
        this.getChildren().add(slider);
        StackPane.setAlignment(slider, Pos.TOP_CENTER);

    }

    public void setModel(ShipModel newModel) {
        model = newModel;
    }

    public void setInteractionModel(InteractionModel newIModel) {
        iModel = newIModel;
    }

    public void setController(ShipController controller) {
        myCanvas.setOnMousePressed(e -> controller.handlePressed(e.getX(),e.getY(), e));
        myCanvas.setOnMouseDragged(e -> controller.handleDragged(e.getX(),e.getY(), e));
        myCanvas.setOnMouseReleased(e -> controller.handleReleased(e.getX(),e.getY(), e));

        controller.setRotationSlider(slider);

    }

    public void draw() {
        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        model.items.forEach(item -> {
            if (iModel.selectedItems.contains(item)){
                gc.setFill(Color.YELLOW);
                gc.setStroke(Color.CORAL);
            } else {
                gc.setStroke(Color.YELLOW);
                gc.setFill(Color.CORAL);
            }
            item.display(gc);

            if(iModel.selectedItems.contains(item) && item.hasChildren()) {
                ((ShipGroup) item).displayBoundingBox(gc);
            }
        });

        double rbLeft = iModel.getRubberBandLeft();
        double rbRight = iModel.getRubberBandRight();
        double rbTop = iModel.getRubberBandTop();
        double rbBottom = iModel.getRubberBandBottom();
        gc.setFill(Color.hsb(30,0.7,1,0.5));
        gc.fillRect(rbLeft, rbTop, (rbRight - rbLeft), (rbBottom - rbTop));
    }

    @Override
    public void modelChanged() {
        draw();
    }
}
