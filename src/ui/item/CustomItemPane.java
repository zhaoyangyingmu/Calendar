package ui.item;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import todoitem.Item;

public class CustomItemPane extends ItemPane {
    private CheckBox hasTimeBox;
    private Label hasTimeLabel;
    private Label contentLabel;
    private TextField contentText;

    public CustomItemPane(Item item) {
        super(item);
        init();
    }

    private void init() {
        hasTimeBox = new CheckBox();
        hasTimeLabel = new Label("设置时间");
        hasTimeBox.setSelected(true);
        contentLabel = new Label("内容：");
        contentText = new TextField();
        bindEvent();
        paint();
    }

    private void bindEvent() {
        hasTimeBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue) {
                startDate.setDisable(false);
                endDate.setDisable(false);
                startHourChoiceBox.setDisable(false);
                startMinuteChoiceBox.setDisable(false);
                endHourChoiceBox.setDisable(false);
                endMinuteChoiceBox.setDisable(false);
            } else {
                startDate.setDisable(true);
                endDate.setDisable(true);
                startHourChoiceBox.setDisable(true);
                startMinuteChoiceBox.setDisable(true);
                endHourChoiceBox.setDisable(true);
                endMinuteChoiceBox.setDisable(true);
            }
        });
    }

    private void paint() {
        int row = getTailRow();
        this.add(hasTimeLabel, 2, 1);
        this.add(hasTimeBox, 4, 1);
        this.add(contentLabel, 0, row);
        this.add(contentText, 1, row++);
        this.add(addChildButton, 1, row);
        this.add(saveButton, 4, row);
    }

}
