package com.example.a3.view;

import javafx.scene.paint.Color;

public class MiniDrawingView extends DrawingView{
    public MiniDrawingView(double width, double height, Color backgroundColor) {
        super(width, height, backgroundColor);
    }

    @Override
    protected void drawSelectionBox() {
        if (this.iModel.getSelection() != null) {
            if (!this.iModel.getSelection().getShapeType().equals("Line")) {
                double x, y, width, height;
                x = this.iModel.getSelection().normX * myCanvas.getWidth();
                y = this.iModel.getSelection().normY * myCanvas.getHeight();
                width = this.iModel.getSelection().normWidth * myCanvas.getWidth();
                height = this.iModel.getSelection().normHeight * myCanvas.getHeight();
                gc.setStroke(Color.RED);
                gc.setLineDashes(null);
                gc.strokeRect(x,y,width,height);
            } else {
                double x1 = this.iModel.getSelection().normX * myCanvas.getWidth();
                double y1 = this.iModel.getSelection().normY * myCanvas.getHeight();
                double x2 = this.iModel.getSelection().normWidth * myCanvas.getWidth();
                double y2 = this.iModel.getSelection().normHeight * myCanvas.getHeight();
                gc.setStroke(Color.RED);
                gc.setLineDashes(null);
                gc.strokeLine(x1,y1,x2,y2);
            }

        }
    }
}
