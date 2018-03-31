package kernel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    private static BorderPane pane = new BorderPane();
    public Display(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane.setTop(MenuPane.getInstance());
        pane.setCenter(BodyPane.getInstance());
        pane.setBottom(QueryPane.getInstance());

        pane.setPadding(Config.getPadding());
        Scene scene = new Scene(pane);
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
            return true;
        }else {
            System.out.println("date "+ date.toString() + " is not valid");
            return false;
        }
    }
}
