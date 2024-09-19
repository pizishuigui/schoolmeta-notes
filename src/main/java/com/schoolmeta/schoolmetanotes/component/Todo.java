package com.schoolmeta.schoolmetanotes.component;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Todo extends AnchorPane {
    private AnchorPane rootPane;

    private AnchorPane titlePane;

    private Label titleLabel;
    private VBox contentPane;

    public Todo() {
        initTitlePane();
        // 初始化rootPane，并指定样式
        rootPane = new AnchorPane();
        AnchorPane.setTopAnchor(titlePane, 2.0);
        AnchorPane.setLeftAnchor(titlePane, 6.0);
        AnchorPane.setRightAnchor(titlePane, 6.0);
        rootPane.getChildren().add(titlePane);
        // 设置rootPane样式
        rootPane.setStyle("-fx-background-color: #f0f0f0; /* 浅灰色背景，可根据需要调整 */" +
                "-fx-border-color: grey; /* 灰色边框 */" +
                "-fx-border-width: 1px; /* 尽可能细的边框宽度，但注意JavaFX可能不支持小于1px的精确值 */" +
                "-fx-border-style: dashed; /* 实线边框 */" +
                "-fx-border-radius: 5px; /* 圆角半径 */");
        // todo 解析样式

        // todo 内容区，使用Text布局

        // 初始化contentPane
        contentPane = new VBox();
        contentPane.setSpacing(10); // 设置子项之间的间距


//        // 创建阴影效果
//        DropShadow dropShadow = new DropShadow();
//        dropShadow.setColor(javafx.scene.paint.Color.rgb(0, 0, 0, 0.5)); // 灰色，50%不透明度
//        dropShadow.setRadius(10); // 模糊半径
//        dropShadow.setSpread(0.5); // 扩展半径（可选，根据需要调整）
//        dropShadow.setOffsetX(0); // X轴偏移
//        dropShadow.setOffsetY(0); // Y轴偏移
//        contentPane.setEffect(dropShadow);

        // 将titlePane和contentPane添加到当前AnchorPane中
        this.getChildren().addAll(titlePane, contentPane);
        AnchorPane.setTopAnchor(titlePane, 0.0);
        AnchorPane.setLeftAnchor(titlePane, 0.0);
        AnchorPane.setRightAnchor(titlePane, 0.0);
        AnchorPane.setBottomAnchor(contentPane, 0.0);
        AnchorPane.setLeftAnchor(contentPane, 0.0);
        AnchorPane.setRightAnchor(contentPane, 0.0);

        this.setStyle("-fx-background-color: #f0f0f0; /* 浅灰色背景，可根据需要调整 */" +
                "-fx-border-color: grey; /* 灰色边框 */" +
                "-fx-border-width: 1px; /* 尽可能细的边框宽度，但注意JavaFX可能不支持小于1px的精确值 */" +
                "-fx-border-style: dashed; /* 实线边框 */" +
                "-fx-border-radius: 5px; /* 圆角半径 */");

        // 示例：添加一些待办事项
        addItem("学习JavaFX");
        addItem("完成项目报告");
        addItem("锻炼身体");
    }

    private void initTitlePane() {
        // 初始化titlePane
        titlePane = new AnchorPane();
        titleLabel = new Label("待办事项列表");
        titlePane.getChildren().add(titleLabel);
        AnchorPane.setTopAnchor(titleLabel, 0.0);
        AnchorPane.setLeftAnchor(titleLabel, 0.0);
        AnchorPane.setRightAnchor(titleLabel, 0.0);

        titlePane.setStyle("-fx-border-color: grey; -fx-border-width: 0,0,5px,0; -fx-border-style: dashed;");
    }

    // 方法：向待办事项列表中添加一个项目
    public void addItem(String itemText) {
        TodoItem todoItem = new TodoItem();
        contentPane.getChildren().add(todoItem);
    }
}
