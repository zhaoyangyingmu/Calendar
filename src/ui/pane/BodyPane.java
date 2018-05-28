package ui.pane;

import holiday.DayManager;
import holiday.DayType;
import io.ItemIO;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.OtherItem;
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
        hBox.setSpacing(Config.gethGap());
        hBox.setPadding(new Insets(10, 0, 10, 0));
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
            assert lastCalendars != null;
            assert calendars != null;
            int lastSize = lastCalendars.size();
            int curSize = calendars.size();
            List<CalendarDate> nextCalendars = DateUtil.getTailOfCalendar(date, lastSize + curSize);
            assert nextCalendars != null;
            int nextSize = nextCalendars.size();

            for (int i = 0; i < lastSize; i++) {
                CalendarDate lastDate = lastCalendars.get(i);
                DayItem item = new NThisMonthDay(new OrdinaryDay(lastDate)).getItem();
                item.setOnMouseClicked(event -> {
                    if (DateUtil.isConsidered(lastDate)) {
                        Display.paintDays(lastDate);
                    }
                });
                this.add(item.getItem(), i, 0);
            }
            int j;
            for (int i = 0; i < curSize; i++) {
                j = i + lastSize;
                DayItem item = new OrdinaryDay(calendars.get(i));
                ArrayList<Item> items = ItemManager.getInstance().getItemsByStamp(from, to);
                if (items.size() > 0) {
                    item = new MemoDay(item, items).getItem();
                }
                String dateString = calendars.get(i).getDateString();
                DayType type;
                if ((type = DayManager.isFestival(dateString)) != null) {
                    item = new Festival(item, type.getName()).getItem(); //获取节日名称
                }
                if ((type = DayManager.isRest(dateString)) != null
                        || (type = DayManager.isWorkDay(dateString)) != null)
                    item = new DayOff(item, type.getName()).getItem();
                if (j % 7 == 0 || j % 7 == 6) {
                    item = new Weekend(item).getItem();
                }
                item.setOnMouseClicked(new SquareClickHandler(from, to));

                from = new TimeStamp(from.getYear(), from.getMonth(),
                        from.getDay() + 1, from.getHour(), from.getMinute());
                to = new TimeStamp(to.getYear(), to.getMonth(),
                        to.getDay() + 1, to.getHour(), to.getMinute());
                this.add(item, j % 7, j / 7);
            }
            this.getChildren().get(lastSize + date.getDay() - 1).setId("focus");
            CalendarDate today = DateUtil.getToday();
            if (today.getYear() == date.getYear() && today.getMonth() == date.getMonth()) {
                this.getChildren().get(lastSize + today.getDay() - 1).setId("today");
            }
            for (int i = 0; i < nextSize; i++) {
                j = i + lastSize + curSize;
                CalendarDate nextDate = nextCalendars.get(i);
                DayItem item = new NThisMonthDay(new OrdinaryDay(nextDate)).getItem();
                item.setOnMouseClicked(event -> {
                    if (DateUtil.isConsidered(nextDate)) {
                        Display.paintDays(nextDate);
                    }
                });
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
//                Display.addDetailPane(from, to);
                if (ItemManager.getInstance().getItemsByStamp(from, to).isEmpty()) {
                    Item item = null;
                    try {
                        item = new OtherItem(from, to, "");
                        ItemManager.getInstance().addItem(item);
                        ItemIO.output();
                        Display.addEditPane(item, true);
                    } catch (Exception e) {
                        Display.showToast("请输入正确的时间与正确的类型！");
                    }
                } else
                    Display.startItemListStage(from, to);

//                Display.addDetailPane(from, to);
            }
        }
    }


}
