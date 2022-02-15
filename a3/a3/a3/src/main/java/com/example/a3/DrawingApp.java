package com.example.a3;

import com.example.a3.controller.DrawingController;
import com.example.a3.imodel.InteractionModel;
import com.example.a3.model.DrawingModel;
import com.example.a3.view.MainUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DrawingApp extends Application {
    @Override
    public void start(Stage stage)  {
        StackPane root = new StackPane();

        MainUI view = new MainUI();
        DrawingModel model = new DrawingModel();
        InteractionModel iModel = new InteractionModel();
        DrawingController controller = new DrawingController();

        view.setInteractionModel(iModel);
        view.setModel(model);
        view.setController(controller);
        controller.setInteractionModel(iModel);
        controller.setModel(model);
        iModel.addSubscriber(view);
        model.addSubscriber(view);

        root.getChildren().add(view);
        Scene scene = new Scene(root);
        stage.setTitle("Drawing Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}