package com.example.a2.view;

import com.example.a2.controller.RecordController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NewRecordView extends StackPane {

    private final Button saveButton;
    private final Button cancelButton;
    private final TextField titleTextField;
    private final TextField projectTextField;

    public NewRecordView() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10, 10, 10, 10));

        // ----------
        Text formTitle = new Text("Record");
        formTitle.setFont(Font.font(20));
        root.add(formTitle, 0,0,2,1);

        // ----------
        Label titleLabel = new Label("Title");
        titleLabel.setFont(Font.font(16));
        root.add(titleLabel, 0, 1);

        titleTextField = new TextField();
        titleTextField.setFont(Font.font(16));
        titleTextField.setPrefWidth(300);
        titleTextField.setMaxWidth(300);
        root.add(titleTextField, 1,1);

        // ----------
        Label projectLabel = new Label("Project");
        projectLabel.setFont(Font.font(16));
        root.add(projectLabel, 0, 2);

        projectTextField = new TextField();
        projectTextField.setFont(Font.font(16));
        root.add(projectTextField, 1,2);

        // ----------
        this.cancelButton = new Button("Cancel");
        this.cancelButton.setFont(Font.font(14));
        this.saveButton = new Button("Save");
        this.saveButton.setFont(Font.font(14));
        HBox buttonsHBox = new HBox(10);
        buttonsHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsHBox.getChildren().addAll(this.cancelButton,this.saveButton);
        root.add(buttonsHBox,1,4);

        this.getChildren().add(root);

    }

    private void clearForm() {
        this.titleTextField.setText("");
        this.projectTextField.setText("");
    }

    public void setController(RecordController recordController) {
        // Save Button
        this.saveButton.setOnAction(e->{
            String title, project;
            title = this.titleTextField.getText();
            project = this.projectTextField.getText();

            // if the title field is empty, then do nothing
            // otherwise, call handleSaveButtonClick
            if (!title.equals("")) {
                recordController.handleSaveButtonClick(title, project);
                clearForm();
            }
        });

        // Cancel Button
        this.cancelButton.setOnAction(e->{
            clearForm();
        });
    }
}
