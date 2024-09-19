package com.schoolmeta.schoolmetanotes;

import com.schoolmeta.schoolmetanotes.component.widget.CircleProgress;
import com.schoolmeta.schoolmetanotes.component.widget.StatusCircle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TodoListApp extends Application {
        @Override  
        public void start(Stage primaryStage) {
            VBox rootPane = new VBox();
            rootPane.setSpacing(6);

            // 测试状态label
            testStatueCircle(rootPane);

            // 测试环形进度
            testCircleProgress(rootPane);

            showStage(primaryStage, rootPane);
        }

    private void testCircleProgress(VBox rootPane) {
        CircleProgress circleProgress = new CircleProgress(10);
        rootPane.getChildren().add(circleProgress);

        HBox buttonPane = new HBox();
        Button button0 = new Button("init");
        button0.setOnAction(actionEvent -> {
            circleProgress.setProgress(0.5);
        });
        buttonPane.getChildren().add(button0);
        Button button1 = new Button("done");
        button1.setOnAction(actionEvent -> {
            circleProgress.setProgress(0.8);
        });
        buttonPane.getChildren().add(button1);
        buttonPane.setSpacing(6);
        rootPane.getChildren().add(buttonPane);
    }

    private void showStage(Stage primaryStage, VBox rootPane) {
        Scene scene = new Scene(rootPane, 800, 800);
        scene.getStylesheets().add(getClass().getResource("widget.css").toExternalForm());
        primaryStage.setTitle("待办事项列表示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void testStatueCircle(VBox rootPane) {
        StatusCircle statusCircle = new StatusCircle(10);
        rootPane.getChildren().add(statusCircle);

        HBox buttonPane = new HBox();
        Button button0 = new Button("init");
        button0.setOnAction(actionEvent -> {
            statusCircle.setValue(1);
        });
        buttonPane.getChildren().add(button0);
        Button button1 = new Button("done");
        button1.setOnAction(actionEvent -> {
            statusCircle.setValue(2);
        });
        buttonPane.getChildren().add(button1);
        buttonPane.setSpacing(6);
        rootPane.getChildren().add(buttonPane);
    }
}