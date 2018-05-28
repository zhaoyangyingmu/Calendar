package ui.pane;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import kernel.Display;
import todoitem.util.TimeStamp;
import ui.util.LabelAndCombo;

public class SearchPane extends GridPane {
    private static SearchPane searchPane;

    private SearchPane() {
        int thisRowIndex = 0;
        Label title = new Label("Detail Search");
        title.setStyle("-fx-font-size: 20px;");
        title.getStyleClass().add("myLabel");
        this.add(title, 0, thisRowIndex++);
        this.setHalignment(title, HPos.CENTER);

        GridPane fromFirstRow = new GridPane();
        Label fromLabel = new Label("From:");
        fromLabel.getStyleClass().add("myLabel");
        fromFirstRow.add(fromLabel, 0, 0);

        GridPane yearUnit = LabelAndCombo.getInstance("year:  ", 1800, 2100);
        ((LabelAndCombo) yearUnit).getComboBox().setMaxSize(70, 25);
        ((LabelAndCombo) yearUnit).getComboBox().setMinSize(70, 25);
        fromFirstRow.add(yearUnit, 1, 0);

        GridPane monthUnit = LabelAndCombo.getInstance("month:  ", 1, 12);
        fromFirstRow.add(monthUnit, 2, 0);
        fromFirstRow.setHgap(10);
        this.add(fromFirstRow, 0, thisRowIndex++);
        this.setHalignment(fromFirstRow, HPos.CENTER);

        GridPane fromSecondRow = new GridPane();
        GridPane dayUnit = LabelAndCombo.getInstance("day:  ", 1, 31);
        fromSecondRow.add(dayUnit, 1, 0);

        GridPane hourUnit = LabelAndCombo.getInstance("hour:  ", 0, 23);
        fromSecondRow.add(hourUnit, 2, 0);

        GridPane minuteUnit = LabelAndCombo.getInstance("minute:  ", 0, 59);
        fromSecondRow.add(minuteUnit, 3, 0);
        this.add(fromSecondRow, 0, thisRowIndex++);
        this.setHalignment(fromSecondRow, HPos.CENTER);

        GridPane toFirstRow = new GridPane();
        Label toLabel = new Label("To:");
        toLabel.getStyleClass().add("myLabel");
        toFirstRow.add(toLabel, 0, 0);

        GridPane toYearUnit = LabelAndCombo.getInstance("year:  ", 1800, 2300);
        ((LabelAndCombo) toYearUnit).getComboBox().setMaxSize(70, 25);
        ((LabelAndCombo) toYearUnit).getComboBox().setMinSize(70, 25);
        toFirstRow.add(toYearUnit, 1, 0);

        GridPane toMonthUnit = LabelAndCombo.getInstance("month:  ", 1, 12);
        toFirstRow.add(toMonthUnit, 2, 0);
        toFirstRow.setHgap(10);
        this.add(toFirstRow, 0, thisRowIndex++);
        this.setHalignment(toFirstRow, HPos.CENTER);

        GridPane toSecondRow = new GridPane();
        GridPane toDayUnit = LabelAndCombo.getInstance("day:  ", 1, 31);
        toSecondRow.add(toDayUnit, 0, 0);

        GridPane toHourUnit = LabelAndCombo.getInstance("hour:  ", 0, 23);
        toSecondRow.add(toHourUnit, 1, 0);

        GridPane toMinuteUnit = LabelAndCombo.getInstance("minute:  ", 0, 59);
        toSecondRow.add(toMinuteUnit, 2, 0);
        toSecondRow.setHgap(3);
        this.add(toSecondRow, 0, thisRowIndex++);
        this.setHalignment(toSecondRow, HPos.CENTER);

        Button searchBt = new Button("search");
        searchBt.setOnMouseClicked(event -> {
            try {
                int fromYear = Integer.parseInt(((LabelAndCombo) yearUnit).getComboBox().getValue());
                int toYear = Integer.parseInt(((LabelAndCombo) toYearUnit).getComboBox().getValue());
                int fromMonth = Integer.parseInt(((LabelAndCombo) monthUnit).getComboBox().getValue());
                int toMonth = Integer.parseInt(((LabelAndCombo) toMonthUnit).getComboBox().getValue());
                int fromDay = Integer.parseInt(((LabelAndCombo) dayUnit).getComboBox().getValue());
                int toDay = Integer.parseInt(((LabelAndCombo) toDayUnit).getComboBox().getValue());
                int fromHour = Integer.parseInt(((LabelAndCombo) hourUnit).getComboBox().getValue());
                int toHour = Integer.parseInt(((LabelAndCombo) toHourUnit).getComboBox().getValue());
                int fromMinute = Integer.parseInt(((LabelAndCombo) minuteUnit).getComboBox().getValue());
                int toMinute = Integer.parseInt(((LabelAndCombo) toMinuteUnit).getComboBox().getValue());
                TimeStamp from = new TimeStamp(fromYear, fromMonth, fromDay, fromHour, fromMinute);
                TimeStamp to = new TimeStamp(toYear, toMonth, toDay, toHour, toMinute);
                if (to.isValid() && from.isValid()) {
//                    Display.addDetailPane(from, to);
                    Display.startItemListStage(from, to);
                    Display.removeSearchPane();
                }
            } catch (Exception e) {
                Display.showToast("Type in number, please! ");
            }
        });
        searchBt.getStyleClass().add("btn");
        searchBt.setMaxSize(90, 60);
        this.add(searchBt, 0, thisRowIndex++);
        this.setHalignment(searchBt, HPos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.setVgap(20);

        final int paneFixWidth = 440;
        final int paneFixHeight = 440;
        this.setMaxSize(paneFixWidth, paneFixHeight);
        this.setMinSize(paneFixWidth, paneFixHeight);
        this.getStyleClass().add("aside");
        this.getStylesheets().add("/stylesheet/buttonAndLabel.css");
        this.getStylesheets().add("/stylesheet/searchPane.css");

    }

    public static SearchPane getInstance() {
        if (searchPane == null) {
            searchPane = new SearchPane();
        }
        return searchPane;
    }
}
