package com.schoolmeta.schoolmetanotes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {
    private StageUpdater stageUpdater;

    private boolean isAlwaysOnTop = false;

    @FXML
    private Button pinBtn;

    public void setStageUpdater(StageUpdater stageUpdater) {
        this.stageUpdater = stageUpdater;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 添加保持窗口最前监听
        pinBtn.setOnAction(this::addAlwaysOnTopListener);
    }
    private void addAlwaysOnTopListener(ActionEvent actionEvent) {
        stageUpdater.updateStageAlwaysOnTop(!isAlwaysOnTop);
        isAlwaysOnTop = !isAlwaysOnTop;
    }
}