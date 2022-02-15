package com.example.a3.model;

import javafx.scene.paint.Color;

public class XRectangle extends XShape{
    public XRectangle(double normX, double normY, double normWidth, double normHeight, Color color, int z) {
        super(normX, normY, normWidth, normHeight, color, z);
    }

    @Override
    public boolean checkHit(double x, double y) {
        return x >= normX && x <= normX+normWidth && y>= normY && y <= normY+normHeight;
    }

    @Override
    public void move(double dX, double dY) {
        normX += dX;
        normY += dY;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public void setZ(int newZ) {
        this.z = newZ;
    }

    @Override
    public String getShapeType() {
        return "Rectangle";
    }
}
