package com.example.assignment1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final ObservableList<ColorPalette> data = FXCollections.observableArrayList(

    );

    private Circle bigCircle;

    private Color currentColor = Color.rgb(50,50,50);

    private final ColorPalette colorPalette = new ColorPalette();

    private PaletteView paletteView;

    private ColorSlider redSlider, greenSlider, blueSlider;

    // helper method to set the color if user sliding the one of the sliders
    private void setColor() {
        int redValue = this.redSlider.getValue();
        int greenValue = this.greenSlider.getValue();
        int blueValue = this.blueSlider.getValue();
        this.bigCircle.setFill(Color.rgb(redValue, greenValue,blueValue));
        this.updateCurrentColor(redValue,greenValue,blueValue);
    }

    private void updateCurrentColor(int redValue, int greenValue, int blueValue) {
        this.currentColor = Color.rgb(redValue, greenValue,blueValue);
    }

    @Override
    public void start(Stage stage) throws IOException {
        HBox rootPane = new HBox();

        VBox leftPane = new VBox(15);
        leftPane.setMinSize(350,625);
        leftPane.setStyle("-fx-background-color: #e8ebe9");
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setPadding(new Insets(20,10,10,10));

        // The top big circle
        this.bigCircle = new Circle(0,0,leftPane.getMinWidth()/3);
        this.bigCircle.setFill(Color.rgb(50,50,50)); // initial value
        leftPane.getChildren().add(this.bigCircle);

        // Sliders
        this.redSlider = new ColorSlider(10,"Red");
        this.redSlider.slider.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.redSlider.setValue(newValue.intValue());
            this.setColor();

        });

        this.greenSlider = new ColorSlider(10,"Green");
        this.greenSlider.slider.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.greenSlider.setValue(newValue.intValue());
            this.setColor();
        });

        this.blueSlider = new ColorSlider(10,"Blue");
        this.blueSlider.slider.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.blueSlider.setValue(newValue.intValue());
            this.setColor();
        });

        leftPane.getChildren().addAll(redSlider, greenSlider, blueSlider);

        // Add to Palette Button
        Button addToPaletteButton = new Button("Add to Palette");
        addToPaletteButton.setPadding(new Insets(10,10,10,10));
        leftPane.getChildren().add(addToPaletteButton);

        this.paletteView = new PaletteView( this.colorPalette);
        leftPane.getChildren().add(paletteView);

        // set eventListener to addToPaletteButton
        addToPaletteButton.setOnAction(event -> {
            colorPalette.addColor(this.currentColor);
            this.paletteView.setNewColor(this.colorPalette);
        });

        // Add to List Button (for the Left Pane)
        Button addToListButton = new Button("Add to List");
        addToListButton.setOnAction(event -> {
            // adding to copy only
            data.add(this.colorPalette.copy());
        });
        addToListButton.setPadding(new Insets(10,10,10,10));
        leftPane.getChildren().add(addToListButton);

        // -----------

        // Right Pane
        VBox rightPane = new VBox();
        rightPane.setMinSize(380,625);

        ListView<ColorPalette> myListView = new ListView<>();
        myListView.setItems(data);
        myListView.setPrefHeight(rightPane.getMinHeight());
        myListView.setCellFactory(listItem -> new PaletteCell());
        rightPane.getChildren().add(myListView);

        // adding left pane and right pane to the root pane
        rootPane.getChildren().addAll(leftPane, rightPane);
        Scene scene = new Scene(rootPane, 730, 625);
        stage.setScene(scene);
        stage.setTitle("Assignment 1");
        stage.setHeight(stage.getScene().getHeight());
        stage.setWidth(stage.getScene().getWidth());
        stage.setMinWidth(730);
        stage.setMinHeight(625);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}