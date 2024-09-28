package com.schoolmeta.notes;

import com.schoolmeta.notes.component.widget.CircleProgress;
import com.schoolmeta.notes.component.widget.StatusCircle;
import com.schoolmeta.notes.infrastructure.db.DBInitialize;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TodoListApp extends Application {
    private ObservableList<String> todoItems = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        VBox rootPane = new VBox();
        rootPane.setSpacing(6);

        // 初始化数据库
        initDB();

        // 使用Vbox绑定list数据实现更新
        // 初始化待办事项
        todoItems.addAll("买牛奶", "写作业", "学习JavaFX");
        todoItems.addListener((ListChangeListener.Change<? extends String> c) -> updateVBox(rootPane));
        updateVBox(rootPane);

        showStage(primaryStage, rootPane);
    }

    private void updateVBox(VBox vBox) {
        vBox.getChildren().removeAll(vBox.getChildren().filtered(node -> !(node instanceof Button)));
        // 移除除了按钮以外的所有子节点
        todoItems.forEach(item -> {
            Button button = new Button(item);
            button.setOnAction(e -> {
                // 这里可以添加删除或完成事项的逻辑
                todoItems.remove(item);
                updateVBox(vBox); // 更新界面
            });
            vBox.getChildren().add(button);
        });
    }

    private void initDB() {
        DBInitialize.initDB();
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

    private void testCircleProgress(VBox rootPane) {
        CircleProgress circleProgress = new CircleProgress(10);
        rootPane.getChildren().add(circleProgress);

        HBox buttonPane = new HBox();
        Button button0 = new Button("0.5");
        button0.setOnAction(actionEvent -> {
            circleProgress.setProgress(0.3);
        });
        buttonPane.getChildren().add(button0);
        Button button1 = new Button("0.8");
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
}