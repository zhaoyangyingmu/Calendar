package ui.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import ui.Config;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class QueryPane extends StackPane {
    private static QueryPane queryPane;
    private GridPane contentGrid;

    public static QueryPane getInstance() {
        if (queryPane == null) {
            queryPane = new QueryPane();
            queryPane.getStylesheets().add(Config.class.getResource("/stylesheet/query.css").toString());
            queryPane.getStylesheets().add(Config.class.getResource("/stylesheet/button.css").toString());
        }
        return queryPane;
    }

    private QueryPane() {
        TextField dateText = new TextField("Search format : 2018-1-1");
        dateText.setMaxSize(200 , 30);
        dateText.setMinSize(200 , 30);
        Button searchBt = new Button("Search");
        searchBt.getStyleClass().add("btn");
        searchBt.setMaxSize(80,30);
        searchBt.setMinSize(80,30);
        searchBt.setCursor(Cursor.HAND);
        searchBt.setOnMouseClicked(event -> {
            String text = dateText.getText();
            if (DateUtil.isFormatted(text)) {
                CalendarDate date = new CalendarDate(text);
                Display.paintDays(date);
            }
        });
        contentGrid = new GridPane();
        contentGrid.add(dateText, 0, 0);
        contentGrid.add(searchBt, 1, 0);
        contentGrid.setHgap(20);
        contentGrid.setAlignment(Pos.CENTER);
        contentGrid.setPadding(new Insets(10,0,0,0));
        this.getChildren().add(contentGrid);
    }
}

