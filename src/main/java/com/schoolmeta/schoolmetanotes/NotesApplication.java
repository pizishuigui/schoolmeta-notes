package com.schoolmeta.schoolmetanotes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NotesApplication extends Application implements StageUpdater {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(NotesApplication.class.getResource("main-frame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("base.css").toExternalForm());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        MainFrameController controller = fxmlLoader.getController();
        controller.setStageUpdater(alwaysOnTop -> primaryStage.setAlwaysOnTop(alwaysOnTop));
    }

    @Override
    public void updateStageAlwaysOnTop(boolean alwaysOnTop) {
        System.out.println(alwaysOnTop);
        primaryStage.setAlwaysOnTop(alwaysOnTop);
    }
}