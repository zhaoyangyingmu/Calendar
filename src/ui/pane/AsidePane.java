package ui.pane;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import kernel.Display;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class AsidePane extends GridPane {
    public AsidePane() {
        Label title = new Label("Detail search");
        title.getStyleClass().add("myLabel");
        this.add(title, 0, 0);
        this.setHalignment(title, HPos.CENTER);

        GridPane fromFirstRow = new GridPane();
        Label fromLabel = new Label("From:");
        fromLabel.getStyleClass().add("myLabel");
        fromFirstRow.add(fromLabel, 0, 0);

        Label fromYearLabel = new Label("year:");
        fromYearLabel.getStyleClass().add("myLabel");
        fromFirstRow.add(fromYearLabel, 1, 0);
        ChoiceBox<String> fromYearChoices = getStringChoices(1800, 2100);
        fromFirstRow.add(fromYearChoices, 2, 0);

        Label fromMonthLabel = new Label("month");
        fromMonthLabel.getStyleClass().add("myLabel");
        fromFirstRow.add(fromMonthLabel, 3, 0);
        ChoiceBox<String> fromMonthChoices = getStringChoices(1, 12);
        fromFirstRow.add(fromMonthChoices, 4, 0);
        fromFirstRow.setHgap(3);
        this.add(fromFirstRow, 0, 1);
        this.setHalignment(fromFirstRow, HPos.CENTER);

        GridPane fromSecondRow = new GridPane();
        Label fromDayLabel = new Label("day:");
        fromDayLabel.getStyleClass().add("myLabel");
        fromSecondRow.add(fromDayLabel, 0, 0);
        ChoiceBox<String> fromDayChoices = getStringChoices(1, 31);
        fromSecondRow.add(fromDayChoices, 1, 0);

        Label fromHourLabel = new Label("hour:");
        fromHourLabel.getStyleClass().add("myLabel");
        fromSecondRow.add(fromHourLabel, 2, 0);
        ChoiceBox<String> fromHourChoices = getStringChoices(0, 23);
        fromSecondRow.add(fromHourChoices, 3, 0);

        Label fromMinuteLabel = new Label("minute:");
        fromMinuteLabel.getStyleClass().add("myLabel");
        fromSecondRow.add(fromMinuteLabel, 4, 0);
        ChoiceBox<String> fromMinuteChoices = getStringChoices(0, 59);
        fromSecondRow.add(fromMinuteChoices, 5, 0);
        fromSecondRow.setHgap(3);
        this.add(fromSecondRow, 0, 2);
        this.setHalignment(fromSecondRow, HPos.CENTER);

        GridPane toFirstRow = new GridPane();
        Label toLabel = new Label("To:");
        toLabel.getStyleClass().add("myLabel");
        toFirstRow.add(toLabel, 0, 0);

        Label toYearLabel = new Label("year:");
        toYearLabel.getStyleClass().add("myLabel");
        toFirstRow.add(toYearLabel, 1, 0);
        ChoiceBox<String> toYearChoices = getStringChoices(1800, 2100);
        toFirstRow.add(toYearChoices, 2, 0);

        Label toMonthLabel = new Label("month");
        toMonthLabel.getStyleClass().add("myLabel");
        toFirstRow.add(toMonthLabel, 3, 0);
        ChoiceBox<String> toMonthChoices = getStringChoices(1, 12);
        toFirstRow.add(toMonthChoices, 4, 0);
        toFirstRow.setHgap(3);
        this.add(toFirstRow, 0, 3);
        this.setHalignment(toFirstRow, HPos.CENTER);

        GridPane toSecondRow = new GridPane();
        Label toDayLabel = new Label("day:");
        toDayLabel.getStyleClass().add("myLabel");
        toSecondRow.add(toDayLabel, 0, 0);
        ChoiceBox<String> toDayChoices = getStringChoices(1, 31);
        toSecondRow.add(toDayChoices, 1, 0);

        Label toHourLabel = new Label("hour:");
        toHourLabel.getStyleClass().add("myLabel");
        toSecondRow.add(toHourLabel, 2, 0);
        ChoiceBox<String> toHourChoices = getStringChoices(0, 23);
        toSecondRow.add(toHourChoices, 3, 0);

        Label toMinuteLabel = new Label("minute:");
        toMinuteLabel.getStyleClass().add("myLabel");
        toSecondRow.add(toMinuteLabel, 4, 0);
        ChoiceBox<String> toMinuteChoices = getStringChoices(0, 59);
        toSecondRow.add(toMinuteChoices, 5, 0);
        toSecondRow.setHgap(3);
        this.add(toSecondRow, 0, 4);
        this.setHalignment(toSecondRow, HPos.CENTER);

        Button searchBt = new Button("search");
        searchBt.setOnMouseClicked(event -> {
            int fromYear = Integer.parseInt(fromYearChoices.getValue());
            int toYear = Integer.parseInt(toYearChoices.getValue());
            int fromMonth = Integer.parseInt(fromMonthChoices.getValue());
            int toMonth = Integer.parseInt(toMonthChoices.getValue());
            int fromDay = Integer.parseInt(fromDayChoices.getValue());
            int toDay = Integer.parseInt(toDayChoices.getValue());
            int fromHour = Integer.parseInt(fromHourChoices.getValue());
            int toHour = Integer.parseInt(toHourChoices.getValue());
            int fromMinute = Integer.parseInt(fromMinuteChoices.getValue());
            int toMinute = Integer.parseInt(toMinuteChoices.getValue());
            TimeStamp from = new TimeStamp(fromYear, fromMonth, fromDay, fromHour, fromMinute);
            TimeStamp to = new TimeStamp(toYear, toMonth, toDay, toHour, toMinute);
            if (to.isValid() && from.isValid()) {
                Display.addDetailPane(from, to);
            }
        });
        searchBt.getStyleClass().add("btn");
        this.add(searchBt, 0, 5);
        this.setHalignment(searchBt, HPos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.setVgap(20);
        this.getStylesheets().add("/stylesheet/buttonAndLabel.css");
        this.getStyleClass().add("aside");
    }

    public ChoiceBox<String> getStringChoices(int from, int to) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            list.add("" + i);
        }
        ChoiceBox<String> choices = new ChoiceBox<>(FXCollections.observableArrayList(list));
        choices.setStyle("-fx-background-color: rgba(255,255,255,0.5);-fx-text-fill: #aaa;");
        choices.setValue("" + from);
        return choices;
    }
}
