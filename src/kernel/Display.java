package kernel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import todoitem.Item;
import todoitem.util.TimeStamp;
import ui.Config;
import ui.pane.*;

import java.util.ArrayList;


/*
* You need to implement Calendar GUI here!
* show the calendar of month of today.
* jump to last/next month's calendar
* jump to last/next year's calendar
*
* jump to one specific day's calendar
* */
public class Display extends Application {
    private static Stage stage = new Stage();
    private static StackPane imageCalendarPane = new StackPane();
    private static GridPane calendarPane = new GridPane();
    private static GridPane calendarWithAside = new GridPane();
    private static DetailPane detailPane;
    private static TimeStamp fromStatic;
    private static TimeStamp toStatic;
    private static ImageView backgroundImage;
    private static EditPane editPane;
    public Display(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarPane.add(MenuPane.getInstance(), 0 , 0);
        calendarPane.add(BodyPane.getInstance(), 0 , 1);
        calendarPane.add(QueryPane.getInstance(), 0 , 2);

        calendarWithAside.add(new AsidePane(),0,0);
        calendarWithAside.add(calendarPane,1,0);
        calendarWithAside.setHgap(10);

        calendarWithAside.setTranslateX(300);
        calendarWithAside.setTranslateY(200);

        backgroundImage = new ImageView(Config.class.getResource("/res/"+DateUtil.getToday().getMonth()+".jpg").toString());
        backgroundImage.setFitHeight(650);
        backgroundImage.setFitWidth(1050);
        imageCalendarPane.getChildren().add(backgroundImage);
        imageCalendarPane.getChildren().add(calendarWithAside);
        Scene scene = new Scene(imageCalendarPane);
        stage.setMaxHeight(Config.getWindowHeight());
        stage.setMinHeight(Config.getWindowHeight());
        stage.setMaxWidth(Config.getWindowWidth());
        stage.setMinWidth(Config.getWindowWidth());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void addDetailPane(TimeStamp from , TimeStamp to) {
        detailPane = new DetailPane(from , to);
        fromStatic = from;
        toStatic = to;
        imageCalendarPane.getChildren().add(detailPane);
        detailPane.setAlignment(Pos.CENTER);
    }

    public static void removeDetailPane() {
        imageCalendarPane.getChildren().remove(detailPane);
    }

    public static void addEditPane(Item item) {
        editPane = new EditPane(item);
        imageCalendarPane.getChildren().add(editPane);
    }

    public static void removeEditPane() {
        imageCalendarPane.getChildren().remove(editPane);
    }


    /**
     * paint the days of whole current month on the frame with the given kernel.CalendarDate
     * @param date a valid kernel.CalendarDate param.
     */
    public static boolean paintDays(CalendarDate date){
        if (date == null){
            System.out.println("date is null!");
            return false;
        }
        if (DateUtil.isValid(date)){
            MenuPane.getInstance().changeChoice(date);
            BodyPane.getInstance().changeContent(date);
            backgroundImage.setImage(new Image(Config.class.getResource("/res/"+date.getMonth()+".jpg").toString()));
            return true;
        }else {
            System.out.println("date "+ date.getYear()+date.getMonth()+ date.getDay() + " is not valid");
            return false;
        }
    }

    private class AsidePane extends GridPane {
        protected AsidePane() {
            Label title = new Label("Detail search");
            title.getStyleClass().add("myLabel");
            this.add(title, 0 , 0);
            this.setHalignment(title, HPos.CENTER);

            GridPane fromFirstRow = new GridPane();
            Label fromLabel = new Label("From:");
            fromLabel.getStyleClass().add("myLabel");
            fromFirstRow.add(fromLabel,0,0);

            Label fromYearLabel = new Label("year:");
            fromYearLabel.getStyleClass().add("myLabel");
            fromFirstRow.add(fromYearLabel,1,0);
            ChoiceBox<String> fromYearChoices = getStringChoices(1800, 2300);
            fromFirstRow.add(fromYearChoices,2,0);

            Label fromMonthLabel = new Label("month");
            fromMonthLabel.getStyleClass().add("myLabel");
            fromFirstRow.add(fromMonthLabel,3,0);
            ChoiceBox<String> fromMonthChoices = getStringChoices(1, 12);
            fromFirstRow.add(fromMonthChoices,4,0);
            fromFirstRow.setHgap(3);
            this.add(fromFirstRow,0,1);
            this.setHalignment(fromFirstRow, HPos.CENTER);

            GridPane fromSecondRow = new GridPane();
            Label fromDayLabel = new Label("day:");
            fromDayLabel.getStyleClass().add("myLabel");
            fromSecondRow.add(fromDayLabel,0,0);
            ChoiceBox<String> fromDayChoices = getStringChoices(1,31);
            fromSecondRow.add(fromDayChoices,1,0);

            Label fromHourLabel = new Label("hour:");
            fromHourLabel.getStyleClass().add("myLabel");
            fromSecondRow.add(fromHourLabel,2,0);
            ChoiceBox<String> fromHourChoices = getStringChoices(0,23);
            fromSecondRow.add(fromHourChoices,3,0);

            Label fromMinuteLabel = new Label("minute:");
            fromMinuteLabel.getStyleClass().add("myLabel");
            fromSecondRow.add(fromMinuteLabel,4,0);
            ChoiceBox<String> fromMinuteChoices = getStringChoices(0,59);
            fromSecondRow.add(fromMinuteChoices,5,0);
            fromSecondRow.setHgap(3);
            this.add(fromSecondRow,0,2);
            this.setHalignment(fromSecondRow, HPos.CENTER);

            GridPane toFirstRow = new GridPane();
            Label toLabel = new Label("To:");
            toLabel.getStyleClass().add("myLabel");
            toFirstRow.add(toLabel,0,0);

            Label toYearLabel = new Label("year:");
            toYearLabel.getStyleClass().add("myLabel");
            toFirstRow.add(toYearLabel,1,0);
            ChoiceBox<String> toYearChoices = getStringChoices(1800, 2300);
            toFirstRow.add(toYearChoices,2,0);

            Label toMonthLabel = new Label("month");
            toMonthLabel.getStyleClass().add("myLabel");
            toFirstRow.add(toMonthLabel,3,0);
            ChoiceBox<String> toMonthChoices = getStringChoices(1, 12);
            toFirstRow.add(toMonthChoices,4,0);
            toFirstRow.setHgap(3);
            this.add(toFirstRow,0,3);
            this.setHalignment(toFirstRow, HPos.CENTER);

            GridPane toSecondRow = new GridPane();
            Label toDayLabel = new Label("day:");
            toDayLabel.getStyleClass().add("myLabel");
            toSecondRow.add(toDayLabel,0,0);
            ChoiceBox<String> toDayChoices = getStringChoices(1,31);
            toSecondRow.add(toDayChoices,1,0);

            Label toHourLabel = new Label("hour:");
            toHourLabel.getStyleClass().add("myLabel");
            toSecondRow.add(toHourLabel,2,0);
            ChoiceBox<String> toHourChoices = getStringChoices(0,23);
            toSecondRow.add(toHourChoices,3,0);

            Label toMinuteLabel = new Label("minute:");
            toMinuteLabel.getStyleClass().add("myLabel");
            toSecondRow.add(toMinuteLabel,4,0);
            ChoiceBox<String> toMinuteChoices = getStringChoices(0,59);
            toSecondRow.add(toMinuteChoices,5,0);
            toSecondRow.setHgap(3);
            this.add(toSecondRow,0,4);
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
                TimeStamp from = new TimeStamp(fromYear, fromMonth, fromDay , fromHour , fromMinute);
                TimeStamp to = new TimeStamp(toYear , toMonth , toDay , toHour , toMinute);
                if (to.isValid() && from.isValid()) {
                    Display.addDetailPane(from, to);
                }
            });
            searchBt.getStyleClass().add("btn");
            this.add(searchBt, 0, 5 );
            this.setHalignment(searchBt, HPos.CENTER);
            this.setAlignment(Pos.CENTER);
            this.setVgap(20);
            this.getStylesheets().add("/stylesheet/buttonAndLabel.css");
            this.getStyleClass().add("aside");
        }
        public ChoiceBox<String> getStringChoices(int from , int to){
            ArrayList<String> list = new ArrayList<>();
            for (int i = from ; i <= to ; i++ ){
                list.add("" + i);
            }
            ChoiceBox<String> choices = new ChoiceBox<>(FXCollections.observableArrayList(list));
            choices.setStyle("-fx-background-color: rgba(255,255,255,0.5);-fx-text-fill: #aaa;");
            choices.setValue(""+from);
            return choices;
        }
    }
}
