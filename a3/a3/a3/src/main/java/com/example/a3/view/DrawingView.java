package com.example.a3.view;

import com.example.a3.controller.DrawingController;
import com.example.a3.imodel.InteractionModel;
import com.example.a3.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class DrawingView{
    Canvas myCanvas;
    GraphicsContext gc;
    DrawingModel model;
    InteractionModel iModel;
    Color backgroundColor;
    PixelReader reader;
    WritableImage buffer;

    public DrawingView(double width, double height, Color backgroundColor) {
        this.backgroundColor = backgroundColor;

        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        gc.setFill(this.backgroundColor);
        gc.fillRect(0,0,width,height);
        buffer = myCanvas.snapshot(null, null);
        reader = buffer.getPixelReader();

        myCanvas.widthProperty().addListener((obs, oldVal, newVal) -> {
            this.draw();
        });
        myCanvas.heightProperty().addListener((obs, oldVal, newVal) -> {
            this.draw();
        });
    }

    public void setController(DrawingController controller) {

        myCanvas.setOnMousePressed(event-> {
            double normX = event.getX()/myCanvas.getWidth();
            double normY = event.getY()/myCanvas.getHeight();
            controller.handlePressed(normX, normY,event);
        });

        myCanvas.setOnMouseReleased(event-> {
            double normX = event.getX()/myCanvas.getWidth();
            double normY = event.getY()/myCanvas.getHeight();
            controller.handleReleased(normX, normY,event);
        });

        myCanvas.setOnMouseDragged(event-> {
            double normX = event.getX()/myCanvas.getWidth();
            double normY = event.getY()/myCanvas.getHeight();
            controller.handleDragged(normX, normY, event);
        });

        myCanvas.setOnMouseMoved(event-> {
            double normX = event.getX()/myCanvas.getWidth();
            double normY = event.getY()/myCanvas.getHeight();
            controller.handleMoved(normX, normY, event);
        });



    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void draw() {
        gc.clearRect(0,0, myCanvas.getWidth(), myCanvas.getWidth());
        gc.setFill(this.backgroundColor);
        gc.fillRect(0,0,myCanvas.getWidth(),myCanvas.getWidth());

        this.model.getItems().forEach(item-> {
            switch(item) {
                case XSquare square -> this.drawSquare(square);
                case XRectangle rectangle -> this.drawRectangle(rectangle);
                case XCircle circle -> this.drawCircle(circle);
                case XOval oval -> this.drawOval(oval);
                case XLine line -> this.drawLine(line);
                case XShape shape -> System.out.println("Error!!! Please Change DrawingView Cases");
            }
        });

        if (this.iModel.getSelection() != null) {
            drawSelectionBox();
        }

    }

    protected void drawSelectionBox() {

        double x, y, width, height;
        double x1,y1,x2,y2;

        if (!this.iModel.getSelection().getShapeType().equals("Line")) {
            x = this.iModel.getSelection().normX * myCanvas.getWidth();
            y = this.iModel.getSelection().normY * myCanvas.getHeight();
            width = this.iModel.getSelection().normWidth * myCanvas.getWidth();
            height = this.iModel.getSelection().normHeight * myCanvas.getHeight();

            gc.setStroke(Color.RED);
            gc.setLineDashes(5.0,5.0);
            gc.strokeRect(x,y,width,height);

            gc.setFill(Color.YELLOW);
            gc.fillOval(x+width-5, y+height-5, 10,10);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1.0);
            gc.setLineDashes(null);
            gc.strokeOval(x+width-5, y+height-5, 10, 10);

        } else {
            x1 = this.iModel.getSelection().normX * myCanvas.getWidth();
            y1 = this.iModel.getSelection().normY * myCanvas.getHeight();
            x2 = this.iModel.getSelection().normWidth * myCanvas.getWidth();
            y2 = this.iModel.getSelection().normHeight * myCanvas.getHeight();

            gc.setStroke(Color.RED);
            gc.setLineDashes(5.0,5.0);
            gc.strokeLine(x1,y1,x2,y2);

            gc.setFill(Color.YELLOW);
            gc.fillOval(x2-5, y2-5, 10,10);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1.0);
            gc.setLineDashes(null);
            gc.strokeOval(x2-5, y2-5, 10, 10);

        }

    }

    private void drawRectangle(XRectangle rectangle) {
        double x = rectangle.normX * myCanvas.getWidth();
        double y = rectangle.normY * myCanvas.getHeight();
        double width = rectangle.normWidth * myCanvas.getWidth();
        double height = rectangle.normHeight * myCanvas.getHeight();

        gc.setFill(rectangle.color);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(null);
        gc.strokeRect(x, y, width, height);
    }

    private void drawSquare(XSquare square) {

        double x = square.normX * myCanvas.getWidth();
        double y = square.normY * myCanvas.getHeight();
        double width = square.normWidth * myCanvas.getWidth();
        double height = square.normHeight * myCanvas.getHeight();

        gc.setFill(square.color);
        gc.fillRect(x, y, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(null);
        gc.strokeRect(x,y,width,height);
    }

    private void drawCircle(XCircle circle) {
        double x = circle.normX * myCanvas.getWidth();
        double y = circle.normY * myCanvas.getHeight();
        double width = circle.normWidth * myCanvas.getWidth();
        double height = width;
        gc.setFill(circle.color);
        gc.fillOval(x, y, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(null);
        gc.strokeOval(x,y,width,height);
    }

    private void drawOval(XOval oval) {
        double x = oval.normX * myCanvas.getWidth();
        double y = oval.normY * myCanvas.getHeight();
        double width = oval.normWidth * myCanvas.getWidth();
        double height = oval.normHeight * myCanvas.getHeight();

        gc.setFill(oval.color);
        gc.fillOval(x, y, width, height);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.0);
        gc.setLineDashes(null);
        gc.strokeOval(x,y,width,height);
    }

    private void drawLine(XLine line) {
        double x1 = line.normX * myCanvas.getWidth();
        double y1 = line.normY * myCanvas.getHeight();
        double x2 = line.normWidth * myCanvas.getWidth();
        double y2 = line.normHeight * myCanvas.getHeight();

        gc.setStroke(line.color);
        gc.setLineWidth(3.0);
        gc.setLineDashes(null);
        gc.strokeLine(x1,y1,x2,y2);
    }

    public void modelChanged() {
        draw();
    }

    public void iModelChanged() {
        draw();
    }

    public void setOnKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE)) {
            this.model.removeItem(this.iModel.getSelection());
            this.iModel.setSelection(null, 0);

        }

    }
}
