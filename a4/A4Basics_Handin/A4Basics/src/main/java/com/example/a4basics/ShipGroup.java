package com.example.a4basics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShipGroup implements Groupable{
    private ArrayList<Groupable> items;
    private double left, right, top, bottom;
    private int zOrder;

    public ShipGroup() {
        items = new ArrayList<>();
        left = 0;
        right = 0;
        top = 0;
        bottom = 0;
        zOrder = 0;
    }

    @Override
    public boolean hasChildren() {
        return !items.isEmpty();
    }

    @Override
    public ArrayList<Groupable> getChildren() {
        return items;
    }

    @Override
    public boolean contains(double x, double y) {
        AtomicBoolean result = new AtomicBoolean(false);
        for (Groupable item : items) {
            if (item.contains(x, y)) {
                result.set(true);
                break;
            }
        }
        return result.get();
    }

    @Override
    public void move(double dx, double dy) {
        items.forEach(item-> {
            item.move(dx,dy);
        });
    }

    @Override
    public double getLeft() {
        reCalculateBoundingBox();
        return left;
    }

    @Override
    public double getRight() {
        reCalculateBoundingBox();
        return right;
    }

    @Override
    public double getTop() {
        reCalculateBoundingBox();
        return top;
    }

    @Override
    public double getBottom() {
        reCalculateBoundingBox();
        return bottom;
    }

    @Override
    public void setZ(int newZ) {
        zOrder = newZ;
        items.forEach(item->{
            item.setZ(newZ);
        });
    }

    @Override
    public boolean isContained(double x, double y, double width, double height) {
        AtomicBoolean result = new AtomicBoolean(true);
        items.forEach(item -> {
            if (!item.isContained(x,y,width,height)) {
                result.set(false);
            }
        });
        return result.get();
    }

    @Override
    public int getZ() {
        return zOrder;
    }

    @Override
    public void display(GraphicsContext gc) {
        items.forEach(item -> {
            item.display(gc);
        });
    }

    @Override
    public Groupable duplicate() {

        ShipGroup result = new ShipGroup();
        items.forEach(item -> {
            result.add(item.duplicate());
        });
        return result;
    }

    @Override
    public void doTheRotation(Double angle) {
        items.forEach(item -> {
            item.doTheRotation(angle);
        });
    }


    public void displayBoundingBox(GraphicsContext gc) {
        reCalculateBoundingBox();
        gc.setStroke(Color.PALEGOLDENROD);
        gc.setLineWidth(1);
        gc.strokeRect(left, top, Math.abs(right-left), Math.abs(top-bottom));
    }

    public void reCalculateBoundingBox() {
        left = Double.POSITIVE_INFINITY;
        right = Double.NEGATIVE_INFINITY;
        top = Double.POSITIVE_INFINITY;
        bottom = Double.NEGATIVE_INFINITY;
        for (Groupable item:items) {
            if (item.getLeft() < left) {
                left = item.getLeft();
            }
            if (item.getRight() > right) {
                right = item.getRight();
            }
            if (item.getTop() < top) {
                top = item.getTop();
            }
            if (item.getBottom() > bottom) {
                bottom = item.getBottom();
            }
        }
    }

    public void add(Groupable item) {
        items.add(item);
    }
}
