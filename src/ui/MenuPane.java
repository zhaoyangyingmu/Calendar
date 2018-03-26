package ui;

import exception.InvalidDateException;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class MenuPane extends StackPane {
    private static MenuPane menuPane;
    private GridPane contentGrid = new GridPane();
    private ChoiceBox<String> yearChoices;
    private ChoiceBox<String> monthChoices;

    public static MenuPane getInstance() {
        if (menuPane == null) {
            menuPane = new MenuPane();
        }
        return menuPane;
    }

    public MenuPane() {
        Label yearLabel = new Label("年份：");
        ArrayList<String> yearList = new ArrayList<>();
        for (int i = 1990; i <= 2020; i++) {
            yearList.add("" + i);
        }
        yearChoices = new ChoiceBox<>(FXCollections.observableArrayList(yearList));
        yearChoices.setValue("2018");

        Label monthLabel = new Label("月份：");
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add("" + i);
        }
        monthChoices = new ChoiceBox<>(FXCollections.observableArrayList(monthList));
        monthChoices.setValue("3");

        Button checkBt = new Button("查看");
        checkBt.setOnMouseClicked(event -> {
            String year = yearChoices.getValue();
            String month = monthChoices.getValue();
            String dateString = year + "-" + month + "-" + 1;
            if (DateUtil.isFormatted(dateString)) {
                CalendarDate date = new CalendarDate(dateString);
                Display.paintDays(date);
            }
        });
        Button todayBt = new Button("今天");
        todayBt.setOnMouseClicked(event -> {
            CalendarDate today = DateUtil.getToday();
            Display.paintDays(today);
        });

        contentGrid.add(yearLabel, 0, 0);
        contentGrid.add(yearChoices, 1, 0);
        contentGrid.add(monthLabel, 2, 0);
        contentGrid.add(monthChoices, 3, 0);
        contentGrid.add(checkBt, 4, 0);
        contentGrid.add(todayBt, 5, 0);
        contentGrid.setAlignment(Pos.CENTER);
        this.getChildren().add(contentGrid);
    }

    public void changeChoice(CalendarDate date) {
        yearChoices.setValue("" + date.getYear());
        monthChoices.setValue("" + date.getMonth());
    }
}
