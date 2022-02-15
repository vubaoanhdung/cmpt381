package com.example.a3.controller;

import com.example.a3.imodel.InteractionModel;
import com.example.a3.model.*;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class DrawingController {
    private InteractionModel iModel;
    private DrawingModel model;
    private double prevX, prevY;

    private enum State {
        READY, DRAGGING, PREPARE_CREATE, SELECTION, MODIFY
    }
    private State currentState;

    public void handlePressed(double normX, double normY,MouseEvent e) {
        prevX = normX;
        prevY = normY;

        switch(currentState) {
            case READY -> {
                if (e.getButton().equals(MouseButton.SECONDARY)) {
                    // Do the moving
                    System.out.println("Moving in the World View");
                } else {
                    boolean hit = model.checkHit(normX, normY);
                    if (hit) {
                        this.iModel.setSelection(this.model.whichItem(normX, normY), this.model.getNextZ());
                        this.currentState = State.SELECTION;
                    } else {
                        this.iModel.setSelection(null, 0);
                        if (this.iModel.getSelectedColor() != null && this.iModel.getSelectedShapeType() != null) {
                            addDummyItem(normX, normY);
                        }
                    }
                }
            }

            case MODIFY -> {
                System.out.println("Modifying");

                if (this.iModel.getSelection() != null) {
                    boolean hit = this.iModel.getSelection().checkHit(normX, normY);
                    if (hit) {
                        this.currentState = State.DRAGGING;
                    }
                    else {
                        checkHitAnother(normX, normY);
                    }
                } else {
                    checkHitAnother(normX, normY);
                }
            }

            case SELECTION -> {
                this.currentState = State.DRAGGING;
            }

            case default -> {
                System.out.println("Error! Please Check DrawingController");
            }
        }
    }

    private void checkHitAnother(double normX, double normY) {
        boolean hitAnother = model.checkHit(normX, normY);
        if (hitAnother) {
            this.iModel.setSelection(this.model.whichItem(normX, normY), this.model.getNextZ());
            this.currentState = State.SELECTION;
        } else {
            addDummyItem(normX, normY);

        }
    }

    private void addDummyItem(double normX, double normY) {
        if (this.iModel.getSelectedShapeType().equals("Line")) {
            this.model.addItem(this.iModel.getSelectedShapeType(),normX, normY, normX, normY, this.iModel.getSelectedColor());
        } else {
            this.model.addItem(this.iModel.getSelectedShapeType(),normX, normY, 0, 0, this.iModel.getSelectedColor());

        }
        this.currentState = State.PREPARE_CREATE;
    }

    public void handleDragged(double normX, double normY, MouseEvent event) {

        double dX = normX - prevX;
        double dY = normY - prevY;

        switch(this.currentState) {

            case DRAGGING -> {
                if (this.iModel.getSelection() != null) {
                    this.model.move(iModel.getSelection(), dX, dY);
                    prevX = normX;
                    prevY = normY;
                } else {
                    addDummyItem(normX, normY);
                }

            }

            case SELECTION -> {
                if (this.iModel.getSelection().checkHit(normX,normY)) {
                    this.currentState = State.DRAGGING;
                } else {
                    boolean hit = model.checkHit(normX, normY);
                    if (hit) {
                        this.iModel.setSelection(this.model.whichItem(normX, normY), this.model.getNextZ());
                        this.currentState = State.SELECTION;
                    }
                }
            }

            case MODIFY -> {
                System.out.println("Scaling");
                this.currentState = State.DRAGGING;
            }

            case PREPARE_CREATE -> {
                int index = this.model.getItems().size() - 1;
                if (this.iModel.getSelectedColor() != null && this.iModel.getSelectedShapeType() != null) {
                    switch (this.iModel.getSelectedShapeType()) {

                        case "Rectangle" -> {

                            double width, height;
                            width = Math.abs(dX);
                            height = Math.abs(dY);

                            double startX, startY;
                            ArrayList<Double> startingPoints = this.getStartingPoints(normX, normY, width, height);
                            startX = startingPoints.get(0);
                            startY = startingPoints.get(1);

                            XRectangle newRectangle = new XRectangle(startX, startY, width, height, this.iModel.getSelectedColor(), this.model.getNextZ());
                            this.model.setItem(index, newRectangle);
                            this.iModel.setSelection(newRectangle, newRectangle.getZ());
                        }

                        case "Square" -> {

                            double width, height;
                            width = Math.abs(dY);
                            height = Math.abs(dY);

                            double startX, startY;
                            ArrayList<Double> startingPoints = this.getStartingPoints(normX, normY, width, height);
                            startX = startingPoints.get(0);
                            startY = startingPoints.get(1);

                            XSquare newSquare = new XSquare(startX, startY, width, height, this.iModel.getSelectedColor(), this.model.getNextZ());
                            this.model.setItem(index, newSquare);
                            this.iModel.setSelection(newSquare, newSquare.getZ());

                        }

                        case "Circle" -> {
                            double width, height;
                            width = Math.abs(dY);
                            height = width;

                            double startX, startY;
                            ArrayList<Double> startingPoints = this.getStartingPoints(normX, normY, width, height);
                            startX = startingPoints.get(0);
                            startY = startingPoints.get(1);

                            XCircle newCircle = new XCircle(startX, startY, width, height, this.iModel.getSelectedColor(), this.model.getNextZ());
                            this.model.setItem(index, newCircle);
                            this.iModel.setSelection(newCircle, newCircle.getZ());
                        }

                        case "Oval" -> {
                            double width, height;
                            width = Math.abs(dX);
                            height = Math.abs(dY);

                            double startX, startY;
                            ArrayList<Double> startingPoints = this.getStartingPoints(normX, normY, width, height);
                            startX = startingPoints.get(0);
                            startY = startingPoints.get(1);

                            XOval newOval = new XOval(startX, startY, width, height, this.iModel.getSelectedColor(), this.model.getNextZ());
                            this.model.setItem(index, newOval);
                            this.iModel.setSelection(newOval, newOval.getZ());
                        }

                        case "Line" -> {
                            double width, height;
                            width = normX;
                            height = normY;

                            XLine newLine = new XLine(prevX, prevY, width, height, this.iModel.getSelectedColor(), this.model.getNextZ());
                            this.model.setItem(index, newLine);
                            this.iModel.setSelection(newLine, newLine.getZ());
                        }
                    }
                }
            }
        }
    }

    public void handleReleased(double normX, double normY, MouseEvent event) {
        switch (this.currentState) {

            case DRAGGING, READY, PREPARE_CREATE, MODIFY -> {
                currentState = State.READY;
            }

            case SELECTION -> {
                this.iModel.setSelection(this.model.whichItem(normX, normY), this.model.getNextZ());
                currentState = State.MODIFY;
            }
        }
    }

    public void handleMoved(double normX, double normY, MouseEvent event) {
    }


    public DrawingController() {
        this.currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void handleNewSelectedShape(ToggleButton b) {
        iModel.setSelectedShape((Shape) b.getGraphic());
        iModel.setSelectedShapeType(b.getText());
    }

    public void handleNewSelectedColour(Color c) {
        iModel.setSelectedColor(c);
    }

    public void setModel(DrawingModel model) {
        this.model = model;
    }

    private ArrayList<Double> getStartingPoints(double normX, double normY, double width, double height) {
        double startX, startY;
        if (normX > prevX) {
            startX = prevX;
        } else {
            startX = prevX - width;
        }

        if (normY > prevY) {
            startY = prevY;
        } else {
            startY = normY;
        }

        ArrayList<Double> result = new ArrayList<>();
        result.add(startX);
        result.add(startY);
        return result;
    }
}
