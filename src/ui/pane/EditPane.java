package ui.pane;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class EditPane extends GridPane {

    public EditPane(Item item) {
        /**
         * from
         * */
        GridPane fromFirstRow = new GridPane();
        TimeStamp from = item.getFrom();
        Label fromLabel = new Label("From: ");
        fromFirstRow.add(fromLabel , 0 , 0);

        GridPane fromYearRow = new LabelAndTextRow("Year: ", from.getYear()+"");
        fromFirstRow.add(fromYearRow , 1 , 0);

        GridPane fromMonthRow = new LabelAndTextRow("Month: ", from.getMonth()+"");
        fromFirstRow.add(fromMonthRow, 2, 0);

        GridPane fromDayRow = new LabelAndTextRow("Day: ", from.getDay()+ "");
        fromFirstRow.add(fromDayRow , 3, 0);
        this.add(fromFirstRow, 0 , 0);


        GridPane fromSecondRow = new GridPane();
        GridPane fromHourRow = new LabelAndTextRow("Hour: " , from.getHour()+ "");
        fromSecondRow.add(fromHourRow , 4, 0);

        GridPane fromMinuteRow = new LabelAndTextRow("Minute: ", from.getMinute() + "");
        fromSecondRow.add(fromMinuteRow, 5, 0);
        this.add(fromSecondRow , 0  , 1 );
        fromSecondRow.setAlignment(Pos.CENTER);

        GridPane toFirstRow = new GridPane();
        TimeStamp to = item.getTo();
        Label toLabel = new Label("To: ");
        toFirstRow.add(toLabel , 0 , 0);

        GridPane toYearRow = new LabelAndTextRow("Year: ", to.getYear()+"");
        toFirstRow.add(toYearRow , 2 , 0);

        GridPane toMonthRow = new LabelAndTextRow("Month: " , to.getMonth()+"");
        toFirstRow.add(toMonthRow, 4, 0);

        GridPane toDayRow = new LabelAndTextRow("Day: ", to.getDay()+"");
        toFirstRow.add(toDayRow , 6, 0);
        this.add(toFirstRow, 0 , 2);

        GridPane toSecondRow = new GridPane();
        GridPane toHourRow = new LabelAndTextRow("Hour: " , to.getHour()+"");
        toSecondRow.add(toHourRow , 8, 0);

        GridPane toMinuteRow = new LabelAndTextRow("Minute: " , to.getMinute()+ "");
        toSecondRow.add(toMinuteRow, 10 , 0);
        this.add(toSecondRow, 0  , 3 );
        toSecondRow.setAlignment(Pos.CENTER);

        GridPane typeRow = new LabelAndTextRow("type: ", item.getItemType().toString());
        this.add(typeRow, 0 , 4);
        typeRow.setAlignment(Pos.CENTER);

        GridPane infoRow = new LabelAndTextRow("Info: ", item.getDetailText());
        this.add(infoRow , 0, 5);
        infoRow.setAlignment(Pos.CENTER);

        GridPane buttonRow = new GridPane();
        Button saveBt = new Button("Save");
        saveBt.setOnMouseClicked(event -> {
            try {
                int[] fromArray = new int[5];
                fromArray[0] = Integer.parseInt(((LabelAndTextRow) fromYearRow).getTextField().getText());
                fromArray[1] = Integer.parseInt(((LabelAndTextRow) fromMonthRow).getTextField().getText());
                fromArray[2] = Integer.parseInt(((LabelAndTextRow) fromDayRow).getTextField().getText());
                fromArray[3] = Integer.parseInt(((LabelAndTextRow) fromHourRow).getTextField().getText());
                fromArray[4] = Integer.parseInt(((LabelAndTextRow) fromMinuteRow).getTextField().getText());

                int[] toArray = new int[5];
                toArray[0] = Integer.parseInt(((LabelAndTextRow) toYearRow).getTextField().getText());
                toArray[1] = Integer.parseInt(((LabelAndTextRow) toMonthRow).getTextField().getText());
                toArray[2] = Integer.parseInt(((LabelAndTextRow) toDayRow).getTextField().getText());
                toArray[3] = Integer.parseInt(((LabelAndTextRow) toHourRow).getTextField().getText());
                toArray[4] = Integer.parseInt(((LabelAndTextRow) toMinuteRow).getTextField().getText());

                TimeStamp fromStamp = new TimeStamp(fromArray[0], fromArray[1], fromArray[2], fromArray[3], fromArray[4]);
                TimeStamp toStamp = new TimeStamp(toArray[0], toArray[1], toArray[2], toArray[3], toArray[4]);
                item.setFrom(fromStamp);
                item.setTo(toStamp);
                Item.ItemType tmpType = Item.ItemType.parseItemType(((LabelAndTextRow) typeRow).getTextField().getText());
                if(tmpType != null) {
                    item.setItemType(tmpType);
                } else {
                    throw new Exception("No such item type!");
                }
                item.setDetailText(((LabelAndTextRow) infoRow).getTextField().getText());
                Display.removeEditPane();
            }catch (Exception e) {
                System.out.println("请输入数字与正确的类型！");
            }
        });
        Button cancelBt = new Button("Cancel");
        cancelBt.setOnMouseClicked(event -> {
            ItemManager.getInstance().deleteItem(item);
            Display.removeEditPane();
        });
        buttonRow.add(saveBt, 0 ,0);
        buttonRow.add(cancelBt , 1, 0);
        this.add(buttonRow , 0, 6);
        buttonRow.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setStyle("-fx-background-color: rgba(255 , 255 , 255 , 0.9);-fx-background-radius: 5.0;");
        this.setMaxSize(683, 315);
        this.setMinSize(683, 315);
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
