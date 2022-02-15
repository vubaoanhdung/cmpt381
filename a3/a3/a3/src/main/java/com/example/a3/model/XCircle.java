package com.example.a3.model;

import javafx.scene.paint.Color;

public class XCircle extends XShape{
    double cx, cy;

    public XCircle(double normX, double normY, double normWidth, double normHeight, Color color, int z) {
        super(normX,normY,normWidth, normHeight, color, z);
        cx = normX + normWidth/2;
        cy = normY + normHeight/2;
    }

    @Override
    public boolean checkHit(double x, double y) {
        return Math.hypot(x-cx, y-cy) <= normWidth/2;
    }

    @Override
    public void move(double dX, double dY) {
        normX += dX;
        normY += dY;
        cx = normX + normWidth/2;
        cy = normY + normHeight/2;
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
        return "Circle";
    }
}