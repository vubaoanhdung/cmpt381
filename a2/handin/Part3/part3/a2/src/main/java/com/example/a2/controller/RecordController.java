package com.example.a2.controller;

import com.example.a2.SummaryWindow;
import com.example.a2.model.RecordModel;
import com.example.a2.utils.DeleteConfirmationDialog;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class RecordController {

    private RecordModel recordModel;

    public RecordController() {
    }

    public void setModel(RecordModel newRecordModel) {
        this.recordModel = newRecordModel;
    }

    public void handleSaveButtonClick(String title, String project) {
        this.recordModel.addRecord(title, project);
    }

    public void handleDeleteButtonClick(int selectedRecordIndex) {
        DeleteConfirmationDialog confirmation = new DeleteConfirmationDialog();
        if (confirmation.getIsDelete()) {
            this.recordModel.removeRecord(selectedRecordIndex);
        }

    }

    public void handleStopButtonClick(int selectedRecordIndex) {
        this.recordModel.stopTimer(selectedRecordIndex);
    }

    public void handleSummaryButtonClick(ActionEvent actionEvent) {
        SummaryWindow summaryWindow = new SummaryWindow();
        summaryWindow.setRecordModel(this.recordModel);
        summaryWindow.start(new Stage());
    }
}
