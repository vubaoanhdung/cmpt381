package com.example.a4basics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.input.*;

import java.util.ArrayList;
import java.util.Optional;

public class ShipController {
    InteractionModel iModel;
    ShipModel model;
    double prevX, prevY;
    double dX, dY;
    Slider slider = null;
    KeyCombination controlX = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN);
    KeyCombination controlC = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
    KeyCombination controlV = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);

    public void handleRotation(Slider slider, Double angle) {
        if (iModel.selectedItems.size() == 0) {
            slider.setValue(0);
        }
        // do the rotation
        iModel.rotating(angle);

    }

    public void setRotationSlider(Slider newSlider) {
        this.slider = newSlider;
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                handleRotation(slider, (Double)newValue-(Double)oldValue);
            }
        });
    }

    protected enum State {
        READY, DRAGGING, RUBBER_BAND
    }

    protected State currentState;

    public ShipController() {
        currentState = State.READY;
    }

    public void setInteractionModel(InteractionModel newModel) {
        iModel = newModel;
    }

    public void setModel(ShipModel newModel) {
        model = newModel;
    }

    public void handlePressed(double x, double y, MouseEvent event) {
        prevX = x;
        prevY = y;
        switch (currentState) {
            case READY -> {
                // context: on a ship?
                Optional<Groupable> hit = model.detectHit(x, y);
                if (hit.isPresent()) {
                    // on ship, so select
                    // if the control is down -> multiple selection
                    if (event.isControlDown()) {
                        model.raiseItem(hit.get());
                        iModel.setSelected(hit.get());
                    }
                    // if the control is NOT down
                    else {
                        if (iModel.selectedItems.contains(hit.get())) {
                            currentState = State.DRAGGING;
                        } else {
                            iModel.clearSelection();
                            iModel.setSelected(hit.get());
                            model.raiseItem(hit.get());
                            currentState = State.DRAGGING;
                        }
                    }

                } else {
                    // on background - is Shift down?
                    if (event.isShiftDown()) {
                        // create ship
                        Ship newShip = model.createShip(x, y);
                        iModel.clearSelection();
                        iModel.setSelected(newShip);
                        currentState = State.DRAGGING;
                    }
                    else {
                        if (!event.isControlDown()) {
                            // clear selection
                            iModel.clearSelection();
                        }
//                        iModel.setRubberBandLeft(x);
//                        iModel.setRubberBandTop(y);
                        iModel.createRubberBand(x,y);
                        currentState = State.RUBBER_BAND;

                    }
                }
            }
        }
    }

    public void handleDragged(double x, double y, MouseEvent event) {
        dX = x - prevX;
        dY = y - prevY;
        prevX = x;
        prevY = y;
        switch (currentState) {
           case DRAGGING -> {
               iModel.selectedItems.forEach(item -> {
                   model.moveItem(item, dX, dY);
               });
           }
            case RUBBER_BAND -> {
                iModel.resizeRubberBand(x,y);

            }
        }
    }

    public void handleReleased(double x, double y, MouseEvent event) {
        switch (currentState) {
            case DRAGGING -> {
                currentState = State.READY;
            }
            case RUBBER_BAND -> {
                double rbLeft = iModel.getRubberBandLeft();
                double rbRight = iModel.getRubberBandRight();
                double rbTop = iModel.getRubberBandTop();
                double rbBottom = iModel.getRubberBandBottom();
                ArrayList<Groupable> items =  model.getItemsContainWithin(rbLeft, rbRight, rbTop, rbBottom);
                items.forEach(item-> {
                    iModel.setSelected(item);
                });
                iModel.resetRubberBand();
                currentState = State.READY;
            }
        }
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        switch(currentState) {
            case READY -> {
                if (keyEvent.getText().compareTo("g") == 0) {
                    // Create a new Group Object
                    ShipGroup group = new ShipGroup();

                    // Put all items from the selection set into the new Group
                    iModel.selectedItems.forEach(item -> {
                        group.add(item);
                    });

                    // Remove all items in the selection set from the model's list
                    iModel.selectedItems.forEach(item-> {
                        model.items.remove(item);
                    });

                    // Add the new group to the model's list
                    model.createShipGroup(group);

                    // Clear the selection set
                    iModel.clearSelection();

                    // Add the new group to the selection set
                    iModel.setSelected(group);
                    model.raiseItem(group);

                } else if (keyEvent.getText().compareTo("u") == 0) {

                    // If only one item in selection set NAD it has children
                    if (iModel.selectedItems.size() == 1 && iModel.selectedItems.get(0).hasChildren()) {

                        //Model
                        //Remove the group from the list
                        model.items.remove(iModel.selectedItems.get(0));
                        // For each child of the group, add the child to the list
                        iModel.selectedItems.get(0).getChildren().forEach(item -> {
                           model.items.add(item);
                        });

                        //iModel
                        // Save the reference of selected items before clearing the selection set
                        ArrayList<Groupable> selections = iModel.selectedItems.get(0).getChildren();
                        // Clear the selection set
                        iModel.clearSelection();
                        // Add each child to the selection set
                        selections.forEach(item -> {
                            iModel.setSelected(item);
                            model.raiseItem(item);
                        });
                    }
                } else {
                    if (iModel.selectedItems.size() != 0) {
                        if (controlX.match(keyEvent)) {
                            iModel.selectedItems.forEach(item -> {
                                model.items.remove(item);
                            });
                            iModel.handleCut();
                            iModel.clearSelection();
                            currentState = State.READY;
                        } else if (controlC.match(keyEvent)) {
                            iModel.handleCopy();
                        } else {
                            // Do nothing
                        }
                    }

                    if (controlV.match(keyEvent)) {
                        iModel.handlePaste();
                        iModel.selectedItems.forEach(item -> {
                            model.items.add(item);
                            model.raiseItem(item);
                        });
                    }

                }
            }
        }

    }
}
