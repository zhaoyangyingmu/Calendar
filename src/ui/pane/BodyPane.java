package ui.pane;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import todoitem.Memo;
import todoitem.MemoManager;
import todoitem.util.TimeStamp;
import ui.Config;
import ui.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class BodyPane extends StackPane {
    private static volatile BodyPane bodyPane;
    private ContentGrid contentGrid;
    private CalendarDate currentDate;
    private VBox vBox;

    public static BodyPane getInstance() {
        if (bodyPane == null) {
            synchronized (BodyPane.class) {
                if (bodyPane == null) {
                    bodyPane = new BodyPane();
                    bodyPane.getStylesheets().add(Config.class.getResource("/stylesheet/body.css").toString());
                    bodyPane.getStyleClass().add("bodyPane");
                }
            }
        }
        return bodyPane;
    }

    private BodyPane() {
        currentDate = DateUtil.getToday();
        contentGrid = new ContentGrid(currentDate);
        vBox = new VBox();
        vBox.getChildren().addAll(getTableHead(), contentGrid);
        this.getChildren().add(vBox);

    }


    public void changeContent(CalendarDate date) {
        currentDate = date;
        vBox.getChildren().remove(contentGrid);
        contentGrid = new ContentGrid(date);
        vBox.getChildren().add(contentGrid);
    }

    public void refresh() {
        changeContent(currentDate);
    }

    private HBox getTableHead() {
        // this
        // table head
        HBox hBox = new HBox();
        for (int i = 0; i < 7; i++) {
            BorderPane pane = new BorderPane();
            Label label = new Label("" + DateUtil.DayType.values()[i].getPrintMark());
            label.setStyle("-fx-text-fill: white;");
            label.setAlignment(Pos.CENTER);
            pane.setPrefSize(Config.getRectangleWidth(), Config.getRectangleHeight());
            pane.getStyleClass().add("basic_day");
            pane.setCenter(label);
            hBox.getChildren().add(pane);
        }
        return hBox;
    }

    private class ContentGrid extends GridPane {
        ContentGrid(CalendarDate date) {
            // table body
            // row processing
            TimeStamp from = new TimeStamp(date.getYear(), date.getMonth(), 1, 0, 0);
            TimeStamp to = new TimeStamp(date.getYear(), date.getMonth(), 1, 23, 59);

            List<CalendarDate> lastCalendars = DateUtil.getHeadOfCalendar(date);
            List<CalendarDate> calendars = DateUtil.getDaysInMonth(date);
            List<CalendarDate> nextCalendars = DateUtil.getTailOfCalendar(date, lastCalendars.size() + calendars.size());
            int lastSize = lastCalendars.size();
            int curSize = calendars.size();
            int nextSize = nextCalendars.size();

            for (int i = 0; i < lastSize; i++) {
                DayItem item = new NThisMonthDay(new OrdinaryDay(lastCalendars.get(i))).getItem();
                this.add(item.getItem(), i, 0);
            }
            int j;
            for (int i = 0; i < curSize; i++) {
                j = i + lastSize;
                DayItem item = new OrdinaryDay(calendars.get(i));
                ArrayList<Memo> memos = MemoManager.getInstance().getItemsByStamp(from, to);
//                if (memos.size() > 0) {
                    item = new MemoDay(item, memos).getItem();
//                }
                //TODO 节日
                if (true) {
                    item = new Festival(item, "五一").getItem(); //获取节日名称
                }
                //TODO 假日
                if (true) {
                    item = new DayOff(item).getItem();
                }
                item.setOnMouseClicked(new SquareClickHandler(from, to));

                from = new TimeStamp(from.getYear(), from.getMonth(),
                        from.getDay() + 1, from.getHour(), from.getMinute());
                to = new TimeStamp(to.getYear(), to.getMonth(),
                        to.getDay() + 1, to.getHour(), to.getMinute());
                this.add(item, j % 7, j / 7);
            }
            this.getChildren().get(lastSize + date.getDay() - 1).setId("today");

            for (int i = 0; i < nextSize; i++) {
                j = i + lastSize + curSize;
                DayItem item = new NThisMonthDay(new OrdinaryDay(nextCalendars.get(i))).getItem();
                this.add(item, j % 7, j / 7);
            }

            this.setVgap(Config.getvGap());
            this.setHgap(Config.gethGap());
        }

        private class SquareClickHandler implements EventHandler<MouseEvent> {
            private TimeStamp from;
            private TimeStamp to;

            SquareClickHandler(TimeStamp from, TimeStamp to) {
                this.from = from;
                this.to = to;
            }

            @Override
            public void handle(MouseEvent mouseEvent) {
                Display.addDetailPane(from, to);
            }
        }
    }


}
