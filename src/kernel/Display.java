package kernel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import ui.Config;
import ui.pane.BodyPane;
import ui.pane.MenuPane;
import ui.pane.QueryPane;


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
    private static ImageView backgroundImage;
    public Display(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarPane.add(MenuPane.getInstance(), 0 , 0);
        calendarPane.add(BodyPane.getInstance(), 0 , 1);
        calendarPane.add(QueryPane.getInstance(), 0 , 2);

        calendarPane.setTranslateX(500);
        calendarPane.setTranslateY(200);

        backgroundImage = new ImageView(Config.class.getResource("/res/"+DateUtil.getToday().getMonth()+".jpg").toString());
        backgroundImage.setFitHeight(650);
        backgroundImage.setFitWidth(1050);
        imageCalendarPane.getChildren().add(backgroundImage);
        imageCalendarPane.getChildren().add(calendarPane);
        Scene scene = new Scene(imageCalendarPane);
        stage.setMaxHeight(Config.getWindowHeight());
        stage.setMinHeight(Config.getWindowHeight());
        stage.setMaxWidth(Config.getWindowWidth());
        stage.setMinWidth(Config.getWindowWidth());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
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
            System.out.println("date "+ date.toString() + " is not valid");
            return false;
        }
    }
}
