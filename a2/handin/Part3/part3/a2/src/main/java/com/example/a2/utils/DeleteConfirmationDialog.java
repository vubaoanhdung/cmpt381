package com.example.a2.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DeleteConfirmationDialog {
    private Boolean isDelete;
    public DeleteConfirmationDialog() {
        this.isDelete = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Delete Record");
        alert.setHeaderText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            isDelete = true;
        }
    }

    public Boolean getIsDelete() {
        return isDelete;
    }
}
