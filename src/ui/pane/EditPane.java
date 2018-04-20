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

import java.util.ArrayList;

public class EditPane extends StackPane {
    private static EditPane editPane;
    private GridPane contentGrid;
    private Label label;
    private TextField mesText;
    private ArrayList<String> activityList;
    private Button saveBt;
    private Button cancelBt;
//    private ChoiceBox<String> activityChoices;

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
