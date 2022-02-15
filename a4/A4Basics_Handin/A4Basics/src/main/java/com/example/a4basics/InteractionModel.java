package com.example.a4basics;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<ShipModelSubscriber> subscribers;
    ArrayList<Groupable> selectedItems;
    double rubberBandStartX, rubberBandStartY;
    double rubberBandLeft, rubberBandRight, rubberBandTop, rubberBandBottom;
    ShipClipboard clipboard;
    double previousAngle;

    public InteractionModel() {
        subscribers = new ArrayList<>();
        selectedItems = new ArrayList<>();
        rubberBandLeft = 0;
        rubberBandRight = 0;
        rubberBandTop = 0;
        rubberBandBottom = 0;
        rubberBandStartX = 0;
        rubberBandStartY = 0;
        clipboard = new ShipClipboard();
        previousAngle = 0;
    }

    public void createRubberBand(double x, double y) {
        rubberBandStartX = x;
        rubberBandStartY = y;
        rubberBandLeft = x;
        rubberBandTop = y;
        rubberBandRight = x;
        rubberBandBottom = y;
    }

    public void clearSelection() {
        selectedItems = new ArrayList<>();
        notifySubscribers();
    }

    public void setSelected(Groupable newSelection) {
        if (selectedItems.contains(newSelection)) {
            selectedItems.remove(newSelection);
        } else {
            selectedItems.add(newSelection);
        }
        notifySubscribers();
    }

    public void resizeRubberBand(double x, double y) {
        rubberBandLeft = Math.min(x,rubberBandStartX);
        rubberBandRight = Math.max(x, rubberBandStartX);
        rubberBandTop = Math.min(y, rubberBandStartY);
        rubberBandBottom = Math.max(y, rubberBandStartY);
        notifySubscribers();
    }

    public void addSubscriber(ShipModelSubscriber aSub) {
        subscribers.add(aSub);
    }

    private void notifySubscribers() {
        subscribers.forEach(sub -> sub.modelChanged());
    }

    public double getRubberBandLeft() {
        return rubberBandLeft;
    }

    public void setRubberBandLeft(double rubberBandLeft) {
        this.rubberBandLeft = rubberBandLeft;
        notifySubscribers();
    }

    public double getRubberBandRight() {
        return rubberBandRight;
    }

    public void setRubberBandRight(double rubberBandRight) {
        this.rubberBandRight = rubberBandRight;
        notifySubscribers();
    }

    public double getRubberBandTop() {
        return rubberBandTop;
    }

    public void setRubberBandTop(double rubberBandTop) {
        this.rubberBandTop = rubberBandTop;
        notifySubscribers();
    }

    public double getRubberBandBottom() {
        return rubberBandBottom;
    }

    public void setRubberBandBottom(double rubberBandBottom) {
        this.rubberBandBottom = rubberBandBottom;
        notifySubscribers();
    }

    public void resetRubberBand() {
        rubberBandLeft = 0;
        rubberBandRight = 0;
        rubberBandTop = 0;
        rubberBandBottom = 0;
        rubberBandStartX = rubberBandLeft;
        rubberBandStartY = rubberBandTop;
        notifySubscribers();
    }

    public void handleCut() {
        clipboard.cut(selectedItems);
        notifySubscribers();
    }

    public void handleCopy() {
        clipboard.copy(selectedItems);
        notifySubscribers();
    }

    public void handlePaste() {
        clearSelection();
        selectedItems = clipboard.getItems();
        notifySubscribers();
    }

    public void rotating(Double angle) {
        selectedItems.forEach(item -> {
            item.doTheRotation(angle);
        });
        notifySubscribers();
    }
}
