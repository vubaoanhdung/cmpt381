package com.example.a2;

import com.example.a2.model.RecordModel;
import com.example.a2.view.SummaryTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SummaryWindow extends Application {

    private RecordModel recordModel;

    @Override
    public void start(Stage stage){

        StackPane root = new StackPane();

        SummaryTableView summaryTableView = new SummaryTableView(this.recordModel);
        root.getChildren().add(summaryTableView);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(TTrack.class.getResource("styles.css").toExternalForm());

        stage.setTitle("Summary");
        stage.setScene(scene);
        stage.setMinWidth(600);
        stage.setMinHeight(350);
        stage.show();

    }

    public void setRecordModel(RecordModel model) {
        this.recordModel = model;
    }

    public static void main(String[] args) {
        launch();
    }

}
