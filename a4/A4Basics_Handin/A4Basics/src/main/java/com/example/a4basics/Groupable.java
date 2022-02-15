package com.example.a4basics;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public interface Groupable {
    boolean hasChildren();
    ArrayList<Groupable> getChildren();
    boolean contains(double x, double y);
    void move(double dx, double dy);
    double getLeft();
    double getRight();
    double getTop();
    double getBottom();
    void setZ(int newZ);

    boolean isContained(double x, double y, double width, double height);

    int getZ();

    void display(GraphicsContext gc);

    Groupable duplicate();

    void doTheRotation(Double angle);
}
