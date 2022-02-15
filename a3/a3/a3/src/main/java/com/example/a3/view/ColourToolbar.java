package com.example.a3.view;

import com.example.a3.imodel.InteractionModel;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ColourToolbar extends StackPane {
    private ArrayList<ToggleButton> colours = new ArrayList<>();
    private InteractionModel iModel;
    private ToggleGroup colourGroup;

    public ColourToolbar() {
        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(5,5,5,5));

        // Creating buttons
        ToggleButton aqua = new ToggleButton("Aqua");
        ToggleButton violet = new ToggleButton("Violet");
        ToggleButton green = new ToggleButton("Green");
        ToggleButton gold = new ToggleButton("Gold");
        ToggleButton orange = new ToggleButton("Orange");
        ToggleButton coral = new ToggleButton("Coral");
        ToggleButton fuchsia = new ToggleButton("Fuchsia");
        ToggleButton peru = new ToggleButton("Peru");

        // Testing only

        // Add buttons to the arraylist
        this.colours.add(aqua);
        this.colours.add(violet);
        this.colours.add(green);
        this.colours.add(gold);
        this.colours.add(orange);
        this.colours.add(coral);
        this.colours.add(fuchsia);
        this.colours.add(peru);

        // set up toggle group
        this.colourGroup = new ToggleGroup();
        this.colours.forEach(c->c.setToggleGroup(this.colourGroup));

        // Set Colours (Style)
        aqua.setStyle("-fx-background-color: AQUA;" + "-fx-background-radius: 15px");
        violet.setStyle("-fx-background-color: VIOLET;" + "-fx-background-radius: 15px");
        green.setStyle("-fx-background-color: GREEN;" + "-fx-background-radius: 15px");
        gold.setStyle("-fx-background-color: GOLD;" + "-fx-background-radius: 15px");
        orange.setStyle("-fx-background-color: ORANGE;" + "-fx-background-radius: 15px");
        coral.setStyle("-fx-background-color: CORAL;" + "-fx-background-radius: 15px");
        fuchsia.setStyle("-fx-background-color: FUCHSIA;" + "-fx-background-radius: 15px");
        peru.setStyle("-fx-background-color: PERU;"+ "-fx-background-radius: 15px");

        // Expand the width of the buttons
        this.colours.forEach(c->c.setMaxWidth(Double.MAX_VALUE));
        // Expand the height of the buttons
        this.colours.forEach(c->c.setMaxHeight(Double.MAX_VALUE));

        // set up row constraint
        RowConstraints rc = new RowConstraints();
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(rc,rc,rc,rc,rc,rc,rc,rc); // 8 rows in total

        // Add all buttons to the root
        for (int i = 0; i <=7; i++) {
            root.add(this.colours.get(i), 0, i);
        }

        this.getChildren().add(root);
    }


    public void iModelChanged() {
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public ToggleGroup getColourGroup() {
        return this.colourGroup;
    }
}
