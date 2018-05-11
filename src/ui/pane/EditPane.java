package ui.pane;

import io.ItemIO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.util.TimeStamp;
import ui.util.LabelAndCombo;

import java.util.ArrayList;

public class EditPane extends GridPane {

    public EditPane(Item item, boolean fromAdd) {
        /**
         * from timeStamp information
         * */
        GridPane fromFirstRow = new GridPane();
        TimeStamp from = item.getFrom();
        Label fromLabel = new Label("From: ");
        fromFirstRow.add(fromLabel , 0 , 0);

        GridPane fromYearRow = LabelAndCombo.getInstance("year:  " , 1800 , 2100);
        ((LabelAndCombo) fromYearRow).getComboBox().setValue(from.getYear()+"");
        ((LabelAndCombo) fromYearRow).getComboBox().setMinSize(70 , 25);
        ((LabelAndCombo) fromYearRow).getComboBox().setMaxSize(70 , 25);
        fromFirstRow.add(fromYearRow , 1 , 0);

        GridPane fromMonthRow = LabelAndCombo.getInstance("month:  " , 1, 12);
        ((LabelAndCombo) fromMonthRow).getComboBox().setValue(from.getMonth()+ "");
        fromFirstRow.add(fromMonthRow, 2, 0);

        GridPane fromDayRow = LabelAndCombo.getInstance("day:  ", 1 , 31);
        ((LabelAndCombo) fromDayRow).getComboBox().setValue(from.getDay()+"");
        fromFirstRow.add(fromDayRow , 3, 0);
        this.add(fromFirstRow, 0 , 0);


        GridPane fromSecondRow = new GridPane();
        GridPane fromHourRow = LabelAndCombo.getInstance("hour:  ", 0 ,23);
        ((LabelAndCombo) fromHourRow).getComboBox().setValue(from.getHour()+"");
        fromSecondRow.add(fromHourRow , 4, 0);

        GridPane fromMinuteRow = LabelAndCombo.getInstance("minute:  " , 0 ,59);
        ((LabelAndCombo) fromMinuteRow).getComboBox().setValue(from.getMinute()+"");
        fromSecondRow.add(fromMinuteRow, 5, 0);
        this.add(fromSecondRow , 0  , 1 );
        fromSecondRow.setAlignment(Pos.CENTER);


        /**
         * to timeStamp information
         * */
        GridPane toFirstRow = new GridPane();
        TimeStamp to = item.getTo();
        Label toLabel = new Label("To: ");
        toFirstRow.add(toLabel , 0 , 0);

        GridPane toYearRow = LabelAndCombo.getInstance("year:  ", 1800 , 2300);
        ((LabelAndCombo) toYearRow).getComboBox().setValue(to.getYear()+"");
        ((LabelAndCombo) toYearRow).getComboBox().setMaxSize(70 , 25);
        ((LabelAndCombo) toYearRow).getComboBox().setMinSize(70 , 25);
        toFirstRow.add(toYearRow , 2 , 0);

        GridPane toMonthRow = LabelAndCombo.getInstance("month:  " , 1, 12);
        ((LabelAndCombo) toMonthRow).getComboBox().setValue(to.getMonth()+"");
        toFirstRow.add(toMonthRow, 4, 0);

        GridPane toDayRow = LabelAndCombo.getInstance("day:  " , 1, 31);
        ((LabelAndCombo) toDayRow).getComboBox().setValue(to.getDay()+"");
        toFirstRow.add(toDayRow , 6, 0);
        this.add(toFirstRow, 0 , 2);

        GridPane toSecondRow = new GridPane();
        GridPane toHourRow = LabelAndCombo.getInstance("hour:  " , 0,23);
        ((LabelAndCombo) toHourRow).getComboBox().setValue(to.getHour()+"");
        toSecondRow.add(toHourRow , 8, 0);

        GridPane toMinuteRow = LabelAndCombo.getInstance("minute:  " , 0 , 59);
        ((LabelAndCombo) toMinuteRow).getComboBox().setValue(to.getMinute()+"");
        toSecondRow.add(toMinuteRow, 10 , 0);
        this.add(toSecondRow, 0  , 3 );
        toSecondRow.setAlignment(Pos.CENTER);

        /**
         * type information
         * */
        // TODO: 2018/5/11  
        ArrayList<String> typeList = new ArrayList<>();
        for(int i = 0; i < Item.ItemType.values().length; i++) {
            typeList.add(Item.ItemType.values()[i].toString());
        }
        GridPane typeRow = LabelAndCombo.getInstance("type: " , typeList);
        ((LabelAndCombo) typeRow).getComboBox().setValue(item.getItemType().toString());
        ((LabelAndCombo) typeRow).getComboBox().setMinSize(100 , 25);
        ((LabelAndCombo) typeRow).getComboBox().setMaxSize(100 , 25);
        this.add(typeRow, 0 , 4);
        typeRow.setAlignment(Pos.CENTER);

        /**
         * Detail information
         * */
        GridPane infoRow = new LabelAndTextRow("Info: ", item.getDetailText());
        this.add(infoRow , 0, 5);
        infoRow.setAlignment(Pos.CENTER);


        /**
         * Save and remove button;
         * */
        GridPane buttonRow = new GridPane();
        Button saveBt = new Button("Save");
        saveBt.getStyleClass().add("green");
        saveBt.setMinSize(70, 35);
        saveBt.setMaxSize(70 , 35);
        saveBt.setOnMouseClicked(event -> {
            try {
                int[] fromArray = new int[5];
                fromArray[0] = Integer.parseInt(((LabelAndCombo) fromYearRow).getComboBox().getValue());
                fromArray[1] = Integer.parseInt(((LabelAndCombo) fromMonthRow).getComboBox().getValue());
                fromArray[2] = Integer.parseInt(((LabelAndCombo) fromDayRow).getComboBox().getValue());
                fromArray[3] = Integer.parseInt(((LabelAndCombo) fromHourRow).getComboBox().getValue());
                fromArray[4] = Integer.parseInt(((LabelAndCombo) fromMinuteRow).getComboBox().getValue());

                int[] toArray = new int[5];
                toArray[0] = Integer.parseInt(((LabelAndCombo) toYearRow).getComboBox().getValue());
                toArray[1] = Integer.parseInt(((LabelAndCombo) toMonthRow).getComboBox().getValue());
                toArray[2] = Integer.parseInt(((LabelAndCombo) toDayRow).getComboBox().getValue());
                toArray[3] = Integer.parseInt(((LabelAndCombo) toHourRow).getComboBox().getValue());
                toArray[4] = Integer.parseInt(((LabelAndCombo) toMinuteRow).getComboBox().getValue());

                TimeStamp fromStamp = new TimeStamp(fromArray[0], fromArray[1], fromArray[2], fromArray[3], fromArray[4]);
                TimeStamp toStamp = new TimeStamp(toArray[0], toArray[1], toArray[2], toArray[3], toArray[4]);
                if (!toStamp.isValid() || !fromStamp.isValid() ) {
                    throw new Exception("TimeStamp not valid! ");
                }
                item.setFrom(fromStamp);
                item.setTo(toStamp);
                Item.ItemType tmpType = Item.ItemType.parseItemType(((LabelAndCombo) typeRow).getComboBox().getValue());
                if(tmpType != null) {
                    item.setItemType(tmpType);
                } else {
                    throw new Exception("No such item type!");
                }
                item.setDetailText(((LabelAndTextRow) infoRow).getTextField().getText());
                ItemIO.output();
                Display.removeEditPane();
            }catch (Exception e) {
                Display.showToast("请输入数字与正确的类型！");
            }
        });
        Button cancelBt = new Button("Cancel");
        cancelBt.getStyleClass().add("red");
        cancelBt.setMinSize(70 , 35);
        cancelBt.setMaxSize(70 , 35);

        cancelBt.setOnMouseClicked(event -> {
            if (fromAdd) {
                ItemManager.getInstance().deleteItem(item);
                ItemIO.output();
            }
            Display.removeEditPane();
        });
        buttonRow.add(saveBt, 0 ,0);
        buttonRow.add(cancelBt , 1, 0);
        buttonRow.setHgap(10);
        this.add(buttonRow , 0, 6);
        buttonRow.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setStyle("-fx-background-color: rgba(255 , 255 , 255 , 0.9);-fx-background-radius: 5.0;");
        this.setMaxSize(683, 315);
        this.setMinSize(683, 315);
        this.getStylesheets().add("/stylesheet/greenAndRed.css");
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
}
