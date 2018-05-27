package ui.pane;

import javafx.scene.layout.StackPane;
import todoitem.util.TimeStamp;
import ui.Config;

public class WrappedDetailPane extends StackPane {

    public WrappedDetailPane(TimeStamp from , TimeStamp to) {
        DetailPane detailPane = new DetailPane(from , to);
        this.getStyleClass().add("mainScene");
        this.getChildren().add(detailPane);
        this.getStylesheets().add(Config.class.getResource("/stylesheet/wrappedDetailPane.css").toString());
    }

}
