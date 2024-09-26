package com.schoolmeta.notes.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TodoItem extends AnchorPane {
    private BooleanProperty expanded = new SimpleBooleanProperty(false);

    public TodoItem(){
        Label title = new Label("标题行");
        Button toggleButton = new Button("展开/折叠");

        VBox content = new VBox();
        content.getChildren().addAll(new Label("内容区 1"), new Label("内容区 2"));
        content.setManaged(false); // 初始时，不管理其布局大小
        content.setVisible(false); // 初始时不显示

        HBox header = new HBox(10, title, toggleButton);
        VBox root = new VBox(header, content);

        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                expanded.set(!expanded.get());
                content.setManaged(expanded.get());
                content.setVisible(expanded.get());
            }
        });

    }
}
