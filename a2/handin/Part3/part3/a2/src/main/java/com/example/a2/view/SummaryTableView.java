package com.example.a2.view;

import com.example.a2.model.RecordModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;

public class SummaryTableView extends StackPane {
    private final RecordModel recordModel;
    private final HashMap<String, String> summaryRecords = new HashMap<>();


    public SummaryTableView(RecordModel model) {
        recordModel = model;
        getSummaryRecords();

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));

        TableColumn<Map.Entry<String, String>, String> projectColumn = new TableColumn<>("Project");
        projectColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> entry) {
                return new SimpleStringProperty(entry.getValue().getKey());
            }
        });

        TableColumn<Map.Entry<String, String>, String> totalTimeColumn = new TableColumn<>("Total Time");
        totalTimeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, String>, String> entry) {
                return new SimpleStringProperty(entry.getValue().getValue());
            }
        });

        ObservableList<Map.Entry<String, String>> items = FXCollections.observableArrayList(summaryRecords.entrySet());
        TableView<Map.Entry<String, String>> table = new TableView<>(items);
        table.setEditable(false);
        table.getColumns().setAll(projectColumn, totalTimeColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        projectColumn.setMaxWidth(1f * Integer.MAX_VALUE * 70);
        totalTimeColumn.setMaxWidth(1f * Integer.MAX_VALUE * 30);
        table.prefWidthProperty().bind(this.widthProperty());
        table.prefHeightProperty().bind(this.heightProperty());

        root.add(table, 0, 0, 4, 1);
        this.getChildren().add(root);
    }

    private void getSummaryRecords() {
        recordModel.getRecords().forEach(r-> {
            String project = r.projectProperty().get();

            int duration = Integer.parseInt(r.durationProperty().get());
            String totalTime;
            if (!summaryRecords.containsKey(project)) {
                totalTime = Integer.toString(duration);
            } else {
                totalTime = Integer.toString(duration + Integer.parseInt(summaryRecords.get(project)));
            }
            summaryRecords.put(project, totalTime);
        });
    }

}
