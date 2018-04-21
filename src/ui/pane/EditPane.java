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
    private static EditPane editPane;
    private GridPane contentGrid;
    private Label label;
    private TextField mesText;
    private ArrayList<String> activityList;
    private Button saveBt;
    private Button cancelBt;
//    private ChoiceBox<String> activityChoices;

    public EditPane(Item item) {
        /**
         * from
         * */
        GridPane fromRow = new GridPane();
        TimeStamp from = item.getFrom();
        Label fromLabel = new Label("From: ");
        fromRow.add(fromLabel , 0 , 0);

        Label fromYear = new Label("Year:");
        fromRow.add(fromYear , 1 , 0);
        TextField fromYearText = new TextField(from.getYear()+"");
        fromRow.add(fromYearText , 2 , 0);

        Label fromMonth = new Label("Month: ");
        fromRow.add(fromMonth, 3 , 0);
        TextField fromMonthText = new TextField(from.getMonth()+"");
        fromRow.add(fromMonthText, 4, 0);

        Label fromDay = new Label("Day: ");
        fromRow.add(fromDay , 5, 0);
        TextField fromDayText = new TextField(from.getDay()+"");
        fromRow.add(fromDayText , 6, 0);

        Label fromHour = new Label("Hour: ");
        fromRow.add(fromHour , 7 , 0);
        TextField fromHourText = new TextField(from.getHour() + "");
        fromRow.add(fromHourText , 8, 0);

        Label fromMinute = new Label("Minute: ");
        fromRow.add(fromMinute , 9 , 0);
        TextField fromMinuteText = new TextField(from.getMinute()+"");
        fromRow.add(fromMinuteText, 10 , 0);
        this.add(fromRow , 0  , 0 );

        GridPane toRow = new GridPane();
        TimeStamp to = item.getTo();
        Label toLabel = new Label("To: ");
        toRow.add(toLabel , 0 , 0);

        Label toYear = new Label("Year:");
        toRow.add(toYear , 1 , 0);
        TextField toYearText = new TextField(to.getYear()+"");
        toRow.add(toYearText , 2 , 0);

        Label toMonth = new Label("Month: ");
        toRow.add(toMonth, 3 , 0);
        TextField toMonthText = new TextField(to.getMonth()+"");
        toRow.add(toMonthText, 4, 0);

        Label toDay = new Label("Day: ");
        toRow.add(toDay , 5, 0);
        TextField toDayText = new TextField(to.getDay()+"");
        toRow.add(toDayText , 6, 0);

        Label toHour = new Label("Hour: ");
        toRow.add(toHour , 7 , 0);
        TextField toHourText = new TextField(to.getHour() + "");
        toRow.add(toHourText , 8, 0);

        Label toMinute = new Label("Minute: ");
        toRow.add(toMinute , 9 , 0);
        TextField toMinuteText = new TextField(to.getMinute()+"");
        toRow.add(toMinuteText, 10 , 0);
        this.add(toRow, 0  , 1 );

        GridPane typeRow = new GridPane();
        Label typeLabel = new Label("type: ");
        typeRow.add(typeLabel , 0 , 0);
        TextField typeText = new TextField(""+ item.getItemType().toString());
        typeRow.add(typeText , 1, 0);
        this.add(typeRow, 0 , 2);

        GridPane infoRow = new GridPane();
        Label infoLabel = new Label("Info: ");
        infoRow.add(infoLabel , 0 , 0);
        TextField infoText = new TextField("" + item.getDetailText());
        infoRow.add(infoText , 1, 0);
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

    /**
     * Disable this method
     * */
    public static EditPane getInstance() {
        if (editPane == null){
            editPane = new EditPane();
        }
        return editPane;
    }
        //set content by date

    public void setContent(CalendarDate date) {
        label.setText(date.toString());
        mesText.setText("What is going on today?");
//        activityChoices.setValue(date.getActivityType().toString());
    }


    /**
     * Disable this method
     * */
    private EditPane() {
        contentGrid = new GridPane();
        CalendarDate date = DateUtil.getToday();
        label = new Label(date.toString());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 20; -fx-text-fill: white");
//        mesText = new TextField();
//        mesText.setText(date.getMessage());
//
//        activityList = new ArrayList<>();
//        for (CalendarDate.ActivityType type : CalendarDate.ActivityType.values()){
//            activityList.add(type.getTypeStr());
//        }
//        activityChoices = new ChoiceBox<>(FXCollections.observableArrayList(activityList));
//        activityChoices.setValue(date.getActivityType().getTypeStr());

        saveBt = new Button("Save");
        saveBt.setOnMouseClicked(event -> {
            Display.removeEdit();
        });
        cancelBt = new Button("Cancel");
        cancelBt.setOnMouseClicked(event -> {
            Display.removeEdit();
        });

        contentGrid.add(label, 0, 0);
        contentGrid.add(mesText , 0, 1);
//        contentGrid.add(activityChoices,0,2);
        contentGrid.add(saveBt,0,3);
        contentGrid.add(cancelBt,0,4);
        this.getChildren().add(contentGrid);
        this.setMaxSize(500, 300);
        this.setMinSize(500,300);
        this.setStyle("-fx-background-color: rgba(0,0,0,0.5)");
        contentGrid.setAlignment(Pos.CENTER);
        contentGrid.setVgap(20);
    }
}
