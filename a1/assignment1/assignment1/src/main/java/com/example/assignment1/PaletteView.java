package com.example.assignment1;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PaletteView extends HBox {
    private ColorPalette colorPalette;
    private Circle c1, c2, c3;

    public PaletteView( ColorPalette colorPalette) {

        if (colorPalette != null) {
            this.colorPalette = colorPalette;
        } else {
            this.colorPalette = new ColorPalette();
        }
        this.c1 = new Circle(0,0,55);
        this.c2 = new Circle(0,0,55);
        this.c3 = new Circle(0,0,55);

        this.c1.setFill(this.colorPalette.getFirstColor());
        this.c2.setFill(this.colorPalette.getSecondColor());
        this.c3.setFill(this.colorPalette.getThirdColor());

        this.c1.setStroke(Color.BLACK);
        this.c2.setStroke(Color.BLACK);
        this.c3.setStroke(Color.BLACK);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(this.c1,this.c2,this.c3);

    }
    public void setNewColor(ColorPalette newColorPalette) {
        this.c1.setFill(newColorPalette.getFirstColor());
        this.c2.setFill(newColorPalette.getSecondColor());
        this.c3.setFill(newColorPalette.getThirdColor());
    }
}
