package com.example.a3.model;

import javafx.scene.paint.Color;

public class XLine extends XShape{
    double ratioA, ratioB, ratioC;
    double length;

    public XLine(double normX, double normY, double normWidth, double normHeight, Color color, int z) {
        super(normX,normY, normWidth, normHeight, color, z);
        length = getLength(normX, normY, normWidth, normHeight);
        calculateRatios();
    }



    @Override
    public boolean checkHit(double x, double y) {
        return ((Math.abs(distanceFromLine(x,y)) <= 0.02) && (y >= (Math.min(normY, normHeight) - 0.01) && y <= (Math.max(normY, normHeight)+0.01)));
    }

    @Override
    public void move(double dX, double dY) {
        normX += dX;
        normY += dY;
        normWidth += dX;
        normHeight += dY;
        calculateRatios();
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
        return "Line";
    }

    private double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1,2));
    }

    private double distanceFromLine(double x, double y) {
        return ratioA * x + ratioB * y + ratioC;
    }

    private double getLength(double x1, double y1, double x2, double y2) {
        return dist(x1,y1,x2,y2);
    }

    private void calculateRatios() {
        ratioA = (normY - normHeight) / length;
        ratioB = (normWidth - normX) / length;
        ratioC = -1 * ((normY - normHeight)*normX + (normWidth-normX)*normY) / length;
    }
}
