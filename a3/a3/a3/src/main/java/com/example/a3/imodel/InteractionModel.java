package com.example.a3.imodel;

import com.example.a3.model.XShape;
import com.example.a3.view.InteractionModelListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class InteractionModel {
    private ArrayList<InteractionModelListener> subs;
    private Color selectedColor;
    private Shape selectedShape;
    private String selectedShapeType;
    private XShape selection;

    public InteractionModel() {
        this.subs = new ArrayList<>();
        selectedColor = null;
        selectedShape = null;
        selection = null;
        selectedShapeType = null;
    }

    public void addSubscriber(InteractionModelListener sub) {
        this.subs.add(sub);
    }

    private void notifySubscribers() {
        this.subs.forEach(s->s.iModelChanged());
    }

    public Color getSelectedColor() {
        return this.selectedColor;
    }

    public Shape getSelectedShape() {
        return this.selectedShape;
    }

    public void setSelection(XShape newSelection, int newZ) {
        this.selection = newSelection;
        if (this.selection != null) {
            newSelection.setZ(newZ);
        }
        notifySubscribers();
    }

    public void setSelectedColor(Color c) {
        this.selectedColor = c;
        notifySubscribers();
    }

    public void setSelectedShape(Shape s) {
        if (this.selectedShape != null) {
            this.selectedShape.setFill(Color.BLACK);
            this.selectedShape.setStroke(Color.BLACK);
        }
        this.selectedShape = s;
        notifySubscribers();
    }

    public void setSelectedShapeType(String type) {
        this.selectedShapeType = type;
    }

    public String getSelectedShapeType() {
        return this.selectedShapeType;
    }


    public com.example.a3.model.XShape getSelection() {
        return this.selection;
    }
}
