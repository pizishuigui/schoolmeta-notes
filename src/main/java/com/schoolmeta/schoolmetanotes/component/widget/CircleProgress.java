package com.schoolmeta.schoolmetanotes.component.widget;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class CircleProgress extends StackPane {
    private final Arc backArc;
    private final Arc progressArc;
    private final DoubleProperty progress = new SimpleDoubleProperty(0);

    public CircleProgress(double radius) {
        // todo 设置length为0.5以下时，弧线中心位置不对

        // 背景圆环
        backArc =  new Arc(radius, radius, radius, radius, 0, 360);
        backArc.setFill(Color.WHITE);
        backArc.setStroke(Color.GRAY);
        backArc.setStrokeWidth(6);
        getChildren().addAll(backArc);

        // 进度圆环
        progressArc = new Arc(radius, radius, radius, radius, 0, 0);
        progressArc.setFill(Color.WHITE);
        progressArc.setStroke(Color.YELLOWGREEN);
        progressArc.setStrokeWidth(6);
        progressArc.setLength(0);
        getChildren().add(progressArc);

        // 绑定进度到Arc的length属性
        progress.addListener((obs, oldValue, newValue) -> {
            double angle = newValue.doubleValue() * 360;
            progressArc.setLength(angle);
        });
    }

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }
}
