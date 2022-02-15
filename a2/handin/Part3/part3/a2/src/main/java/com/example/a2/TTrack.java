package com.example.a2;

import com.example.a2.controller.RecordController;
import com.example.a2.model.RecordModel;
import com.example.a2.view.NewRecordView;
import com.example.a2.view.OverviewTableView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TTrack extends Application {
    @Override
    public void start(Stage stage) {

        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));

        // Create and Connect MVC
        // New Record Form MVC
        NewRecordView newRecordView = new NewRecordView();
        RecordModel recordModel = new RecordModel();
        RecordController recordController = new RecordController();

        newRecordView.setController(recordController);
        recordController.setModel(recordModel);

        // OverviewTable MVC
        OverviewTableView overviewTableView = new OverviewTableView();

        overviewTableView.setModel(recordModel);
        overviewTableView.setController(recordController);
        recordModel.addSubscriber(overviewTableView);

        root.getChildren().addAll(newRecordView, overviewTableView);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(TTrack.class.getResource("styles.css").toExternalForm());
        overviewTableView.prefHeightProperty().bind(scene.heightProperty());

        stage.setTitle("TTrack Application");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();


        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}