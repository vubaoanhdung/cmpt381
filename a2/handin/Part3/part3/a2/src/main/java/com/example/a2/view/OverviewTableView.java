package com.example.a2.view;

import com.example.a2.controller.RecordController;
import com.example.a2.model.Record;
import com.example.a2.model.RecordModel;
import com.example.a2.model.RecordModelSubscriber;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class OverviewTableView extends StackPane implements RecordModelSubscriber {
    private RecordModel recordModel;
    private final TableView<Record> table;
    private final SimpleIntegerProperty selectedRecordIndex = new SimpleIntegerProperty(-1);
    private final Button deleteButton;
    private final Button stopButton;
    private final Button summaryButton;

    public void setModel(RecordModel newRecordModel) {
        this.recordModel = newRecordModel;
    }

    public void setController(RecordController recordController) {
        deleteButton.setOnAction(e->{
            recordController.handleDeleteButtonClick(this.selectedRecordIndex.getValue());
            table.getSelectionModel().clearSelection();
        });

        stopButton.setOnAction(e->{
            recordController.handleStopButtonClick(this.selectedRecordIndex.getValue());
        });

        summaryButton.setOnAction(recordController::handleSummaryButtonClick);
    }

    @Override
    public void modelUpdated() {
        table.setItems(this.recordModel.getRecords());
    }

    public OverviewTableView() {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        table = new TableView<>();
        table.setEditable(true);
        table.prefWidthProperty().bind(this.widthProperty());
        table.prefHeightProperty().bind(this.heightProperty());


        TableColumn<Record, String> startColumn = new TableColumn<>("Start");
        startColumn.setCellValueFactory(cellData->cellData.getValue().startProperty());

        TableColumn<Record, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData->cellData.getValue().titleProperty());

        TableColumn<Record, String> projectColumn = new TableColumn<>("Project");
        projectColumn.setCellValueFactory(cellData->cellData.getValue().projectProperty());

        TableColumn<Record, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData->cellData.getValue().durationProperty());

        table.getColumns().addAll(startColumn, titleColumn, projectColumn, durationColumn);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        startColumn.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        titleColumn.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        projectColumn.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        durationColumn.setMaxWidth(1f * Integer.MAX_VALUE * 15);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelectedRecord, newSelectedRecord)->{
            if (newSelectedRecord != null) {
                this.selectedRecordIndex.set(table.getSelectionModel().getSelectedIndex());
            } else {
                this.selectedRecordIndex.set(-1);
            }
        });

        root.add(table, 0, 0, 2, 1);

        // -----
        deleteButton = new Button("Delete");
        deleteButton.setFont(Font.font(16));
        deleteButton.disableProperty().bind(this.selectedRecordIndex.isEqualTo(-1));
        stopButton = new Button("Stop");
        stopButton.setFont(Font.font(16));
        stopButton.disableProperty().bind(this.selectedRecordIndex.isEqualTo(-1));

        HBox editButtonsHBox = new HBox(15);
        editButtonsHBox.setAlignment(Pos.BOTTOM_LEFT);
        editButtonsHBox.getChildren().addAll(deleteButton, stopButton);
        root.add(editButtonsHBox, 0,1);

        //-----
        summaryButton = new Button("Summary");
        summaryButton.setFont(Font.font(16));

        HBox summaryButtonHBox = new HBox();
        summaryButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        summaryButtonHBox.getChildren().add(summaryButton);
        root.add(summaryButtonHBox, 1,1);

        this.getChildren().add(root);
    }

}
