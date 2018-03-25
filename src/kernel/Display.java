package kernel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import ui.BodyPane;
import ui.MenuPane;
import ui.QueryPane;

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
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Init the UI Windows here. For example, the frame, some panels and buttons.
     */
    public void init(){

    }

    /**
     * paint the days of whole current month on the frame with the given kernel.CalendarDate
     * @param date a valid kernel.CalendarDate param.
     */
    private void paintDays(CalendarDate date){

    }
}
