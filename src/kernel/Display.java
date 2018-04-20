package kernel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    public Display(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarPane.add(MenuPane.getInstance(), 0 , 0);
        calendarPane.add(BodyPane.getInstance(), 0 , 1);
        calendarPane.add(QueryPane.getInstance(), 0 , 2);

        calendarWithAside.add(new AsidePane(),0,0);
        calendarWithAside.add(calendarPane,1,0);

        calendarWithAside.setTranslateX(500);
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

    public static void returnDetailPane() {
        imageCalendarPane.getChildren().remove(detailPane);
        detailPane = new DetailPane(fromStatic , toStatic);
        imageCalendarPane.getChildren().add(detailPane);
        detailPane.setAlignment(Pos.CENTER);
    }

    public static void addEdit(CalendarDate date) {
        EditPane.getInstance().setContent(date);
        imageCalendarPane.getChildren().add(EditPane.getInstance());
        EditPane.getInstance().setAlignment(Pos.CENTER);
    }

    public static void removeEdit() {
        imageCalendarPane.getChildren().remove(EditPane.getInstance());
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
            this.add(title, 0 , 0);

            GridPane fromFirstRow = new GridPane();
            Label fromLabel = new Label("From:");
            fromFirstRow.add(fromLabel,0,0);

            Label fromYearLabel = new Label("year:");
            fromFirstRow.add(fromYearLabel,1,0);
            ChoiceBox<String> fromYearChoices = getStringChoices(1800, 2300);
            fromFirstRow.add(fromYearChoices,2,0);

            Label fromMonthLabel = new Label("month");
            fromFirstRow.add(fromMonthLabel,3,0);
            ChoiceBox<String> fromMonthChoices = getStringChoices(1, 12);
            fromFirstRow.add(fromMonthChoices,4,0);
            this.add(fromFirstRow,0,1);

            GridPane fromSecondRow = new GridPane();
            Label fromDayLabel = new Label("month:");
            fromSecondRow.add(fromDayLabel,0,0);
            ChoiceBox<String> fromDayChoices = getStringChoices(1,31);
            fromSecondRow.add(fromDayChoices,1,0);

            Label fromHourLabel = new Label("hour:");
            fromSecondRow.add(fromHourLabel,2,0);
            ChoiceBox<String> fromHourChoices = getStringChoices(0,23);
            fromSecondRow.add(fromHourChoices,3,0);

            Label fromMinuteLabel = new Label("minute:");
            fromSecondRow.add(fromMinuteLabel,4,0);
            ChoiceBox<String> fromMinuteChoices = getStringChoices(0,59);
            fromSecondRow.add(fromMinuteChoices,5,0);
            this.add(fromSecondRow,0,2);

            GridPane toFirstRow = new GridPane();
            Label toLabel = new Label("To:");
            toFirstRow.add(toLabel,0,0);

            Label toYearLabel = new Label("year:");
            toFirstRow.add(toYearLabel,1,0);
            ChoiceBox<String> toYearChoices = getStringChoices(1800, 2300);
            toFirstRow.add(toYearChoices,2,0);

            Label toMonthLabel = new Label("month");
            toFirstRow.add(toMonthLabel,3,0);
            ChoiceBox<String> toMonthChoices = getStringChoices(1, 12);
            toFirstRow.add(toMonthChoices,4,0);
            this.add(toFirstRow,0,3);

            GridPane toSecondRow = new GridPane();
            Label toDayLabel = new Label("month:");
            toSecondRow.add(toDayLabel,0,0);
            ChoiceBox<String> toDayChoices = getStringChoices(1,31);
            toSecondRow.add(toDayChoices,1,0);

            Label toHourLabel = new Label("hour:");
            toSecondRow.add(toHourLabel,2,0);
            ChoiceBox<String> toHourChoices = getStringChoices(0,23);
            toSecondRow.add(toHourChoices,3,0);

            Label toMinuteLabel = new Label("minute:");
            toSecondRow.add(toMinuteLabel,4,0);
            ChoiceBox<String> toMinuteChoices = getStringChoices(0,59);
            toSecondRow.add(toMinuteChoices,5,0);
            this.add(toSecondRow,0,4);

            Button searchBt = new Button("search");
            this.add(searchBt, 0, 5 );
        }
        public ChoiceBox<String> getStringChoices(int from , int to){
            ArrayList<String> list = new ArrayList<>();
            for (int i = from ; i <= to ; i++ ){
                list.add("" + i);
            }
            ChoiceBox<String> choices = new ChoiceBox<>(FXCollections.observableArrayList(list));
            choices.setStyle("-fx-background-color: rgba(255,255,255,0.5)");
            return choices;
        }
    }
}
