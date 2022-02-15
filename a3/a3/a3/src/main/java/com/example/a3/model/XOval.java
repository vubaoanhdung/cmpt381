package com.example.a3.model;

import javafx.scene.paint.Color;

public class XOval extends XShape{
    double cx, cy;

    public XOval(double normX, double normY, double normWidth, double normHeight, Color color, int z) {
        super(normX,normY,normWidth, normHeight, color, z);
        cx = normX + normWidth/2;
        cy = normY + normHeight/2;
    }

    @Override
    public boolean checkHit(double x, double y) {
        double semiMajorAxis = Math.max(normWidth, normHeight)/2;
        double semiMinorAxis = Math.min(normWidth, normHeight)/2;
        double dX = x - cx;
        double dY = y - cy;
        return (Math.pow((dX/semiMajorAxis),2) + Math.pow((dY/semiMinorAxis),2)) <= 1;
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
        return "Oval";
    }
}