package com.example.assignment1;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColorSlider extends HBox {
    private Label label;
    public Slider slider; // public so that listeners can be attached from the main application
    private Label value;


    public ColorSlider(double v,String label) {
        super(v);

        this.label = new Label(label);
        VBox vBoxLabel = new VBox();
        vBoxLabel.getChildren().add(this.label);
        vBoxLabel.setMinWidth(40);
        this.slider = new Slider(0,255,50);
        this.value = new Label("50");

        this.getChildren().addAll(vBoxLabel, this.slider, this.value);

    }

    public int getValue() {
        return Integer.parseInt(this.value.getText());
    }

    public void setValue(int newValue) {
        this.value.setText(Integer.toString(newValue));
    }
}
