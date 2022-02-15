package com.example.a3.model;

import javafx.scene.paint.Color;

public abstract class XShape {
    public double normX;
    public double normY;
    public double normWidth;
    public double normHeight;
    public Color color;
    public int z;

    public XShape() {
        normX = 0;
        normY = 0;
        normWidth = 0;
        normHeight = 0;
        color = Color.BLACK;
        z = 0;
    }

    public XShape(double newX, double newY, double newWidth, double newHeight, Color color, int z) {
        this.normX = newX;
        this.normY = newY;
        this.normWidth = newWidth;
        this.normHeight = newHeight;
        this.color = color;
        this.z = z;
    }

    public abstract boolean checkHit(double x, double y);

    public abstract void move(double dX, double dY);

    public abstract int getZ();

    public abstract void setZ(int newZ);

    public abstract String getShapeType();
}
