package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class QueryPane extends StackPane{
    private static QueryPane queryPane;
    private GridPane contentGrid;
    public static QueryPane getInstance(){
        if (queryPane == null){
            queryPane = new QueryPane();
        }
        return queryPane;
    }

    private QueryPane(){
        TextField dateText = new TextField("Search format : 2018-1-1");
        Button searchBt = new Button("查询");
        contentGrid = new GridPane();
        contentGrid.add(dateText,0,0);
        contentGrid.add(searchBt,1,0);
        contentGrid.setAlignment(Pos.CENTER);
        this.getChildren().add(contentGrid);
    }
}
