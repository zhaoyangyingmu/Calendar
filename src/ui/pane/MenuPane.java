package ui.pane;

import exception.InvalidDateException;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import ui.Config;

import java.util.ArrayList;

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
            menuPane.setPadding(new Insets(0, 0, 10, 0));
            menuPane.getStylesheets().add(Config.class.getResource("/stylesheet/buttonAndLabel.css").toString());
        }
        return menuPane;
    }

    public MenuPane() {
        Label yearLabel = new Label("Year: ");
        yearLabel.setStyle("-fx-text-fill: white;");
        ArrayList<String> yearList = new ArrayList<>();
        for (int i = 1800; i <= 2300; i++) {
            yearList.add("" + i);
        }
        yearChoices = new ChoiceBox<>(FXCollections.observableArrayList(yearList));
        yearChoices.setValue(DateUtil.getToday().getYear() + "");
        yearChoices.setStyle("-fx-background-color: rgba(255,255,255,0.5)");


        Label monthLabel = new Label("Month: ");
        monthLabel.setStyle("-fx-text-fill: white;");
        ArrayList<String> monthList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            monthList.add("" + i);
        }
        monthChoices = new ChoiceBox<>(FXCollections.observableArrayList(monthList));
        monthChoices.setValue("" + DateUtil.getToday().getMonth());
        monthChoices.setStyle("-fx-background-color: rgba(255,255,255,0.5)");

        Button checkBt = new Button("Check");
        checkBt.getStyleClass().add("btn");
        checkBt.setMaxSize(60, 30);
        checkBt.setMinSize(60, 30);
        checkBt.setCursor(Cursor.HAND);
        checkBt.setOnMouseClicked(event -> {
            String year = yearChoices.getValue();
            String month = monthChoices.getValue();
            String dateString = year + "-" + month + "-" + 1;
            try {
                CalendarDate date = new CalendarDate(dateString);
                Display.paintDays(date);
            } catch (InvalidDateException e) {
                System.out.println(e.toString());
            }
        });
        Button todayBt = new Button("Today");
        todayBt.getStyleClass().add("btn");
        todayBt.setMaxSize(60, 30);
        todayBt.setMinSize(60, 30);
        todayBt.setCursor(Cursor.HAND);
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
        contentGrid.setHgap(20);
        contentGrid.setAlignment(Pos.CENTER);
        this.getChildren().add(contentGrid);
    }

    public void changeChoice(CalendarDate date) {
        yearChoices.setValue("" + date.getYear());
        monthChoices.setValue("" + date.getMonth());
    }
}
