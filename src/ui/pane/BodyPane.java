package ui.pane;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.util.TimeStamp;
import ui.Config;

import java.util.ArrayList;
import java.util.List;

import static todoitem.Item.ItemType.DATING;
import static todoitem.Item.ItemType.STUDY;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class BodyPane extends StackPane {
    private static BodyPane bodyPane;
    private ContentGrid contentGrid;
    private CalendarDate currentDate;

    public static BodyPane getInstance() {
        if (bodyPane == null) {
            bodyPane = new BodyPane();
            bodyPane.getStylesheets().add(Config.class.getResource("/stylesheet/body.css").toString());
            bodyPane.getStyleClass().add("bodyPane");
        }
        return bodyPane;
    }

    public BodyPane() {
        currentDate = DateUtil.getToday();
        contentGrid = new ContentGrid(currentDate);
        this.getChildren().add(contentGrid);
    }

    public void changeContent(CalendarDate date) {
        currentDate = date;
        this.getChildren().remove(contentGrid);
        contentGrid = new ContentGrid(date);
        this.getChildren().add(contentGrid);
    }

    public void refresh() {
        changeContent(currentDate);
    }

    private class ContentGrid extends GridPane {
        public ContentGrid(CalendarDate date){
            // this

            // table head
            for (int i = 0 ; i<7 ; i++){
                StackPane pane = new StackPane();
                Label label = new Label(""+ DateUtil.DayType.values()[i].getPrintMark());
                label.setStyle("-fx-text-fill: white;");
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
                pane.setMaxSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                pane.setMinSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                pane.getStyleClass().add("thead");
                this.add(pane,i,0);
            }

            // table body
            // row processing
            List<CalendarDate> calendars = DateUtil.getDaysInMonth(date);
            int dayOfWeekBegin = calendars.get(0).getDayOfWeek()%7;
            int dayOfWeekEnd = calendars.get(calendars.size()-1).getDayOfWeek();
            dayOfWeekEnd = 6 - dayOfWeekEnd;
            if (dayOfWeekEnd < 0){
                dayOfWeekEnd = 6;
            }
            int printTotal = dayOfWeekBegin + calendars.size() + dayOfWeekEnd;
            int weekNum = printTotal/7;

            TimeStamp from = new TimeStamp(date.getYear() , date.getMonth(), 1, 0 , 0);
            TimeStamp to = new TimeStamp(date.getYear(), date.getMonth() , 1, 23 , 59);
            for (int row = 1 ; row <= weekNum ; row++){
                for(int column = 0; column < 7; column++){
                    StackPane pane = new StackPane();
                    int index = (row-1)*7 + column + 1 - dayOfWeekBegin - 1;
                    String labelStr = "";
                    boolean isToday = false;

                    // true day
                    if (index >= 0 && index < calendars.size()){
                        labelStr += calendars.get(index).getDay();
                        ArrayList<Item> items = ItemManager.getInstance().getItemsByStamp(from, to);
                        if (items.size() > 0) {
                            Item.ItemType activityType = items.get(0).getItemType();
                            if (activityType == STUDY) {
                                pane.getStyleClass().add("study");
                            } else if (activityType == DATING) {
                                pane.getStyleClass().add("dating");
                            } else {
                                pane.getStyleClass().add("leisure");
                            }
                        }else {
                            pane.getStyleClass().add("leisure");
                        }
                        if (calendars.get(index).equals(DateUtil.getToday())){
                            isToday = true;
                        }
                        pane.setOnMouseClicked(new SquareClickHandler(from ,to));

                        // timeStamp move on
                        from = new TimeStamp(from.getYear(), from.getMonth() ,
                                from.getDay()+1, from.getHour(), from.getMinute());
                        to = new TimeStamp(to.getYear() , to.getMonth() ,
                                to.getDay()+1 ,to.getHour(), to.getMinute());
                    }else {
                        pane.getStyleClass().add("none");
                    }

                    Label label = new Label(labelStr);
                    label.setStyle("-fx-text-fill: white;");
                    if (isToday){
                        pane.setId("today");
                        isToday = false;
                    }
                    label.setAlignment(Pos.CENTER);
                    pane.getChildren().add(label);
                    pane.setMaxSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                    pane.setMinSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                    this.add(pane,column,row);
                }
            }
            this.setVgap(Config.getvGap());
            this.setHgap(Config.gethGap());
        }
        private class SquareClickHandler implements EventHandler<MouseEvent> {
            private TimeStamp from;
            private TimeStamp to;
            public SquareClickHandler(TimeStamp from , TimeStamp to) {
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
