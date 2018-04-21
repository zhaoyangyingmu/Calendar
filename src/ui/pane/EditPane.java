package ui.pane;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class EditPane extends GridPane {

    public EditPane(Item item) {
        /**
         * from
         * */
        GridPane fromRow = new GridPane();
        TimeStamp from = item.getFrom();
        Label fromLabel = new Label("From: ");
        fromRow.add(fromLabel , 0 , 0);

        GridPane fromYearRow = new LabelAndTextRow("Year: ", from.getYear()+"");
        fromRow.add(fromYearRow , 1 , 0);

        GridPane fromMonthRow = new LabelAndTextRow("Month: ", from.getMonth()+"");
        fromRow.add(fromMonthRow, 2, 0);

        GridPane fromDayRow = new LabelAndTextRow("Day: ", from.getMonth()+ "");
        fromRow.add(fromDayRow , 3, 0);

        GridPane fromHourRow = new LabelAndTextRow("Hour: " , from.getDay()+ "");
        fromRow.add(fromHourRow , 4, 0);

        GridPane fromMinuteRow = new LabelAndTextRow("Minute: ", from.getMinute() + "");
        fromRow.add(fromMinuteRow, 5, 0);
        this.add(fromRow , 0  , 0 );

        GridPane toRow = new GridPane();
        TimeStamp to = item.getTo();
        Label toLabel = new Label("To: ");
        toRow.add(toLabel , 0 , 0);

        GridPane toYearRow = new LabelAndTextRow("Year: ", to.getYear()+"");
        toRow.add(toYearRow , 2 , 0);

        GridPane toMonthRow = new LabelAndTextRow("Month: " , to.getMonth()+"");
        toRow.add(toMonthRow, 4, 0);

        GridPane toDayRow = new LabelAndTextRow("Day: ", to.getDay()+"");
        toRow.add(toDayRow , 6, 0);

        GridPane toHourRow = new LabelAndTextRow("Hour: " , to.getHour()+"");
        toRow.add(toHourRow , 8, 0);

        GridPane toMinuteRow = new LabelAndTextRow("Minute: " , to.getMinute()+ "");
        toRow.add(toMinuteRow, 10 , 0);
        this.add(toRow, 0  , 1 );

        GridPane typeRow = new LabelAndTextRow("type: ", item.getItemType().toString());
        this.add(typeRow, 0 , 2);

        GridPane infoRow = new LabelAndTextRow("Info: ", item.getDetailText());
        this.add(infoRow , 0, 3);

        GridPane buttonRow = new GridPane();
        Button saveBt = new Button("Save");
        saveBt.setOnMouseClicked(event -> {
            Display.removeEditPane();
        });
        Button cancelBt = new Button("Cancel");
        cancelBt.setOnMouseClicked(event -> {
            Display.removeEditPane();
        });
        buttonRow.add(saveBt, 0 ,0);
        buttonRow.add(cancelBt , 1, 0);
        this.add(buttonRow , 0, 4);
    }


    private class LabelAndTextRow extends GridPane{
        private Label label;
        private TextField textField;
        public LabelAndTextRow(String labelStr, String textStr) {
            label = new Label(labelStr);
            textField = new TextField(textStr);
            this.add(label , 0 , 0);
            this.add(textField , 1, 0);
        }

        public Label getLabel() {
            return label;
        }

        public TextField getTextField() {
            return textField;
        }
    }
//  public void setContent(Item item)
// TODO: 2018/4/21

}
