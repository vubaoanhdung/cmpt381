package com.example.a3.model;

import com.example.a3.view.DrawingModelListener;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DrawingModel {
    private ArrayList<DrawingModelListener> subs;
    private ArrayList<XShape> items;
    private int nextZ;

    public DrawingModel() {
        items = new ArrayList<>();
        subs = new ArrayList<>();
        nextZ = 0;
    }

    public void addSubscriber(DrawingModelListener sub) {
        this.subs.add(sub);
    }

    private void notifySubscribers() {
        this.subs.forEach(s->s.modelChanged());
    }

     public void addItem(String shapeType, double normX, double normY, double normWidth, double normHeight, Color color) {
        if (shapeType != null) {
                switch (shapeType) {

                    case "Rectangle" -> {
                        this.items.add(new XRectangle(normX, normY, normWidth, normHeight, color, getNextZ()));
                        notifySubscribers();
                    }
                    case "Square" -> {
                        this.items.add(new XSquare(normX, normY, normWidth, normHeight, color, getNextZ()));
                        notifySubscribers();
                    }
                    case "Circle" -> {
                        this.items.add(new XCircle(normX, normY, normWidth, normHeight, color, getNextZ()));
                        notifySubscribers();
                    }
                    case "Oval" -> {
                        this.items.add(new XOval(normX, normY, normWidth, normHeight, color, getNextZ()));
                        notifySubscribers();
                    }
                    case "Line" -> {
                        this.items.add(new XLine(normX, normY, normWidth, normHeight, color, getNextZ()));
                        notifySubscribers();
                    }

                    case default -> {
                        System.out.println("Error!!! Please check DrawingModel");
                    }
                }

            }

     }


    public List<XShape> getItems() {
        this.items.sort(Comparator.comparingInt(XShape::getZ));
        return this.items;
    }

    public boolean checkHit(double x, double y) {
        return this.items.stream().anyMatch(item-> item.checkHit(x, y)
        );
    }

    public XShape whichItem(double x, double y) {
        XShape found = null;
        for (XShape s : items) {
            if (s.checkHit(x, y)) {
                found = s;
            }
        }
        return found;
    }

    public void move(XShape selection, double dX, double dY) {
        selection.move(dX, dY);
        notifySubscribers();
    }

    public void setItem(int index, XShape newShape) {
        this.items.set(index, newShape);
        notifySubscribers();
    }

    public void removeLastItem() {
        this.items.remove(this.items.size() - 1);
        notifySubscribers();
    }


    public int getNextZ() {
        int result = this.nextZ;
        this.nextZ++;
        return result;
    }

    public void removeItem(XShape item) {
        this.getItems().remove(item);
        notifySubscribers();
    }
}
