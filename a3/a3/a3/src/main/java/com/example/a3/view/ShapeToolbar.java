package com.example.a3.view;

import com.example.a3.imodel.InteractionModel;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class ShapeToolbar extends StackPane{
    private ArrayList<ToggleButton> shapes = new ArrayList<>();
    private ToggleGroup shapeGroup;
    private InteractionModel iModel;

    public ShapeToolbar() {
        GridPane root = new GridPane();
        // Creating Shapes
        Rectangle rectangleShape = new Rectangle(50,30);
        Rectangle squareShape = new Rectangle(40,40);
        Circle circleShape = new Circle(20);
        Ellipse ovalShape = new Ellipse(20,15);
        Line lineShape = new Line(10,10,30,30);

        // Creating Buttons
        ToggleButton rectangle = new ToggleButton("Rectangle", rectangleShape);
        ToggleButton square = new ToggleButton("Square", squareShape);
        ToggleButton circle = new ToggleButton("Circle", circleShape);
        ToggleButton oval = new ToggleButton("Oval", ovalShape);
        ToggleButton line = new ToggleButton("Line", lineShape);

        this.shapes.add(rectangle);
        this.shapes.add(square);
        this.shapes.add(circle);
        this.shapes.add(oval);
        this.shapes.add(line);

        // set up the group for the buttons
        this.shapeGroup = new ToggleGroup();
        this.shapes.forEach(s->s.setToggleGroup(shapeGroup));

        // Position the texts under the shapes
        this.shapes.forEach(s -> s.setContentDisplay(ContentDisplay.TOP));

        // expand the width of the buttons
        this.shapes.forEach(s->s.setMaxWidth(Double.MAX_VALUE));

        // expand the height of the buttons
        this.shapes.forEach(s -> s.setMaxHeight(Double.MAX_VALUE));
        // set up row constraint
        RowConstraints rc = new RowConstraints();
        rc.setFillHeight(true);
        rc.setVgrow(Priority.ALWAYS);
        root.getRowConstraints().addAll(rc,rc,rc,rc,rc); // 5 rows in total

        for (int i = 0; i <= 4; i++) {
            root.add(this.shapes.get(i), 0, i);
        }

        this.getChildren().add(root);
    }

    public void setInteractionModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void iModelChanged() {
        Shape s = this.iModel.getSelectedShape();
        Color c = this.iModel.getSelectedColor();

        if (s != null && c != null) {
            s.setStroke(c);
            s.setFill(c);
        }
    }

    public ToggleGroup getShapeGroup() {
        return this.shapeGroup;
    }
}
