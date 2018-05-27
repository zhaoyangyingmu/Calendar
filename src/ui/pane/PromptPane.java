package ui.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import kernel.Display;
import todoitem.Item;
import todoitem.itemSub.AppointmentItem;
import ui.Config;

public class PromptPane extends GridPane {
    private static PromptPane promptPane;
    private Item item;
    private Label label;

    private PromptPane() {
        int row = 0 ;
        int col = 0;
        Label promptLabel = new Label("提示");
        promptLabel.getStyleClass().add("promptLabel");
        this.add(promptLabel , col , row++);
        label = new Label("李华，你约会要迟到了。哈哈哈！！！");
        label.getStyleClass().add("hintLabel");
        this.add(label, col , row++);
        Label knowBt = new Label("朕知道了");
        knowBt.getStyleClass().add("button");
        Label setBt = new Label("设置");
        setBt.getStyleClass().add("button");
        GridPane buttons = new GridPane();
        buttons.getStyleClass().add("buttons");
        buttons.add(setBt, 0 , 0);
        setBt.setOnMouseClicked(event -> {
            Display.addPromptSetPane();
        });

        buttons.add(knowBt , 1, 0);
        knowBt.setOnMouseClicked(event -> {
            Display.removePromptPane();
        });

        buttons.setAlignment(Pos.CENTER);
        buttons.setHgap(10);
        this.add(buttons, col , row++);
        this.setMaxSize(260, 150);
        this.setMinSize(260 , 150);
        this.getStyleClass().add("mainPrompt");
        this.getStylesheets().add(Config.class.getResource("/stylesheet/prompt.css").toString());
        this.setTranslateX(10);
        this.setTranslateY(10);
        this.setLayoutX(10);
        this.setLayoutY(10);
        this.relocate(10,10);
    }

    public static PromptPane getInstance() {
        if(promptPane == null) {
            promptPane = new PromptPane();
        }
        return promptPane;
    }

    public void setItem(Item item) {
        this.item = item;
        label.setText(item.getDetailText());
    }
}
