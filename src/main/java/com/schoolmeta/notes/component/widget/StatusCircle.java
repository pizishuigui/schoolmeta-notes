package com.schoolmeta.notes.component.widget;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StatusCircle extends Label {
  
    private final Circle circle; // 假设圆形半径为50
    private final IntegerProperty valueProperty = new SimpleIntegerProperty(this, "value", 1);
  
    public StatusCircle(int radius) {
        // 设置这个Region的图形为Circle
        circle = new Circle(radius);
        setGraphic(circle);  
  
        // 监听valueProperty的变化来更新颜色  
        valueProperty.addListener((obs, oldValue, newValue) -> {  
            updateColor(newValue.intValue());  
        });  
  
        // 初始颜色设置  
        updateColor(1);  
    }  
  
    // 根据值更新颜色  
    private void updateColor(int value) {  
        switch (value) {  
            case 1:  
                circle.setFill(Color.RED);
                break;  
            case 2:  
                circle.setFill(Color.GREEN);  
                break;  
            case 3:  
                circle.setFill(Color.BLUE);  
                break;  
            case 4:  
                circle.setFill(Color.YELLOW);  
                break;  
            default:  
                circle.setFill(Color.GRAY);  
        }  
    }  
  
    // Getter和Setter  
    public int getValue() {  
        return valueProperty.get();  
    }  
  
    public void setValue(int value) {  
        valueProperty.set(value);  
    }  
  
    public IntegerProperty valueProperty() {  
        return valueProperty;  
    }  
}