package com.example.assignment1;

import javafx.scene.paint.Color;

// to store three Color objects (white by default)
public class ColorPalette {
    private int cursorIndex = 0;

    private Color firstColor;
    private Color secondColor;
    private Color thirdColor;

    public ColorPalette() {
        this.firstColor = Color.rgb(255, 255, 255);
        this.secondColor = Color.rgb(255, 255, 255);
        this.thirdColor = Color.rgb(255, 255, 255);
    }

    public ColorPalette(Color firstColor, Color secondColor, Color thirdColor) {
        this.firstColor = firstColor;
        this.secondColor = secondColor;
        this.thirdColor = thirdColor;
    }

    public ColorPalette copy() {
        Color firstColor = this.getFirstColor();
        Color secondColor = this.getSecondColor();
        Color thirdColor = this.getThirdColor();
        return new ColorPalette(firstColor, secondColor, thirdColor);
    }

    public void addColor(Color color) {
        switch (this.cursorIndex) {
            case 0 -> {
                firstColor = color;
                this.cursorIndex = 1;
            }
            case 1 -> {
                secondColor = color;
                this.cursorIndex = 2;
            }
            case 2 -> {
                thirdColor = color;
                this.cursorIndex = 0;
            }
            default -> {
            }
        }
    }

    public String toString() {
        String firstColorStringRepresentation = getColorStringRepresentation(this.firstColor);
        String secondColorStringRepresentation = getColorStringRepresentation(this.secondColor);
        String thirdColorStringRepresentation = getColorStringRepresentation(this.thirdColor);
        return firstColorStringRepresentation + "\n" + secondColorStringRepresentation + "\n" + thirdColorStringRepresentation;
    }

    private String getColorStringRepresentation(Color c) {
        int redValue = (int) Math.round(c.getRed() * 255);
        int greenValue = (int) Math.round(c.getGreen() * 255);
        int blueValue = (int) Math.round(c.getBlue() * 255);
        return String.format("(%d,%d,%d)", redValue, greenValue, blueValue);
    }

    public Color getFirstColor() {
        return firstColor;
    }
    public Color getSecondColor() {
        return secondColor;
    }
    public Color getThirdColor() {
        return thirdColor;
    }
}
