package ui.pane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import todoitem.Item;

public class PromptPane extends GridPane {
    private static PromptPane promptPane;
    private Item item;
    private Label label;

    private PromptPane() {
        label = new Label("");
        this.add(label, 0 , 0);
        Button knowBt = new Button("朕知道了");
        Button setBt = new Button("设置");
        GridPane buttons = new GridPane();
        buttons.add(setBt, 0 , 0);
        buttons.add(knowBt , 1, 0);
        this.add(buttons, 0 , 1);
    }

    public static PromptPane getInstance() {
        if(promptPane == null) {
            promptPane = new PromptPane();
        }
        return promptPane;
    }

    public void setItem(Item item) {
        this.item = item;

    }

}
