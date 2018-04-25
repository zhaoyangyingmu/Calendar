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
import ui.util.LabelAndCombo;

import java.util.ArrayList;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class MenuPane extends StackPane {
    private static MenuPane menuPane;
    private GridPane contentGrid = new GridPane();
    private GridPane yearUnit;
    private GridPane monthUnit;

    public static MenuPane getInstance() {
        if (menuPane == null) {
            menuPane = new MenuPane();
            menuPane.setPadding(new Insets(0, 0, 10, 0));
            menuPane.getStylesheets().add(Config.class.getResource("/stylesheet/buttonAndLabel.css").toString());
        }
        return menuPane;
    }

    public MenuPane() {
        yearUnit = LabelAndCombo.getInstance("Year:   ", 1800, 2100);
        ((LabelAndCombo) yearUnit).getComboBox().setMaxSize(70, 25);
        ((LabelAndCombo) yearUnit).getComboBox().setMinSize(70 , 25);
        ((LabelAndCombo) yearUnit).getComboBox().setValue(DateUtil.getToday().getYear() + "");

        monthUnit = LabelAndCombo.getInstance("Month:   ", 1, 12);
        ((LabelAndCombo) monthUnit).getComboBox().setValue("" + DateUtil.getToday().getMonth());

        Button checkBt = new Button("Check");
        checkBt.getStyleClass().add("btn");
        checkBt.setMaxSize(60, 30);
        checkBt.setMinSize(60, 30);
        checkBt.setCursor(Cursor.HAND);
        checkBt.setOnMouseClicked(event -> {
            try {
                String year = ((LabelAndCombo) yearUnit).getComboBox().getValue();
                String month = ((LabelAndCombo) monthUnit).getComboBox().getValue();
                String dateString = year + "-" + month + "-" + 1;
                CalendarDate date = new CalendarDate(dateString);
                Display.paintDays(date);
            } catch (InvalidDateException e) {
                Display.showToast(e.toString());
            }catch (Exception e){
                Display.showToast("Type in number, please! ");
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

        int colIndex = 0;
        contentGrid.add(yearUnit, colIndex++, 0);
        contentGrid.add(monthUnit, colIndex++, 0);
        contentGrid.add(checkBt, colIndex++, 0);
        contentGrid.add(todayBt, colIndex++, 0);
        contentGrid.setHgap(20);
        contentGrid.setAlignment(Pos.CENTER);
        this.getChildren().add(contentGrid);
    }

    public void changeChoice(CalendarDate date) {
        ((LabelAndCombo)yearUnit).getComboBox().setValue(""+date.getYear());
        ((LabelAndCombo)monthUnit).getComboBox().setValue("" + date.getMonth());
    }
}
