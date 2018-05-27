package ui.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.Display;
import todoitem.AheadTimeType;
import todoitem.Item;
import todoitem.TimeInterType;
import ui.Config;
import ui.util.LabelAndCombo;

import java.util.ArrayList;

public class PromptSetPane extends StackPane {
    private static PromptSetPane promptSetPane;
    private Item item;

    public static PromptSetPane getInstance() {
        if (promptSetPane  == null) {
            promptSetPane =  new PromptSetPane();
        }
        return promptSetPane;
    }
    private PromptSetPane() {
        GridPane grid = new GridPane();
        int row = 0 ;
        int col = 0;
        Label promptLabel = new Label("提示设置");
        promptLabel.getStyleClass().add("promptLabel");
        grid.add(promptLabel , col , row++);


        ArrayList<String> promptStr = new ArrayList<>();
        promptStr.add("是");
        promptStr.add("否");
        LabelAndCombo promptOnCombo = LabelAndCombo.getInstance("是否提醒", promptStr);

        ArrayList<String> aheadTimeStr = new ArrayList<>();
        for (int i = 0 ; i < AheadTimeType.values().length; i++) {
            aheadTimeStr.add(AheadTimeType.values()[i].getInfo());
        }
        LabelAndCombo aheadTime = LabelAndCombo.getInstance("提前：" , aheadTimeStr);

        ArrayList<String> promptModeStr = new ArrayList<>();
        promptModeStr.add("页面提示区提醒");
        promptModeStr.add("弹出框提醒");
        LabelAndCombo promptMode = LabelAndCombo.getInstance("提醒方式：" , promptModeStr);

        ArrayList<String> interModeStr = new ArrayList<>();
        for (int i = 0 ; i < TimeInterType.values().length; i++) {
            interModeStr.add(TimeInterType.values()[i].getInfo());
        }
        LabelAndCombo interMode = LabelAndCombo.getInstance("间隔：" , interModeStr);

        GridPane checkGrid = new GridPane();
        int checkCol = 0;
        int checkRow = 0;
        checkGrid.add(promptOnCombo,checkCol , checkRow++);
        promptOnCombo.getComboBox().getEditor().setEditable(false);
        promptOnCombo.getComboBox().getStyleClass().add("combo1");
        checkGrid.add(aheadTime , checkCol , checkRow++);
        aheadTime.getComboBox().getEditor().setEditable(false);
        aheadTime.getComboBox().getStyleClass().add("combo2");
        checkGrid.add(promptMode , checkCol , checkRow++);
        promptMode.getComboBox().getEditor().setEditable(false);
        promptMode.getComboBox().getStyleClass().add("combo3");
        checkGrid.add(interMode, checkCol, checkRow++);
        interMode.getComboBox().getEditor().setEditable(false);
        interMode.getComboBox().getStyleClass().add("combo4");
        checkGrid.getStyleClass().add("checkGrid");
        checkGrid.setVgap(10);

        grid.add(checkGrid, col , row++);
        checkGrid.setAlignment(Pos.CENTER);

        Label cancelBt = new Label("取消");
        cancelBt.getStyleClass().add("button");
        cancelBt.setOnMouseClicked(event -> {
            Display.removePromptSetPane();
        });
        Label confirmBt = new Label("确定");
        confirmBt.getStyleClass().add("button");
        confirmBt.setOnMouseClicked(event -> {
            // TODO: 2018/5/27  
            Display.removePromptSetPane();
        });
        GridPane buttons = new GridPane();
        buttons.getStyleClass().add("buttons");
        buttons.add(confirmBt, 0 , 0);
        buttons.add(cancelBt , 1, 0);
        buttons.setAlignment(Pos.CENTER);
        buttons.setHgap(10);
        grid.add(buttons, col , row++);
        grid.setMaxSize(390, 300);
        grid.setMinSize(390 , 300);
        grid.getStyleClass().add("mainPrompt");

        this.getChildren().add(grid);
        this.getStyleClass().add("mainScene");
        this.getStylesheets().add(Config.class.getResource("/stylesheet/promptset.css").toString());
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
