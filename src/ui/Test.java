package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoitem.Item;
import todoitem.itemSub.CourseItem;
import todoitem.util.TimeStamp;
import ui.item.CourseItemPane;
import ui.item.ItemPane;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Item item = new CourseItem(new TimeStamp(2018, 5, 29, 13, 30),
                new TimeStamp(2018, 5, 29, 15, 10),
                "软件工程", "《软件工程》第十一章", "彭鑫", "上课前要提问", "Z2107", "18", "3");
        ItemPane pane = new CourseItemPane(item);
        pane.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(pane));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
