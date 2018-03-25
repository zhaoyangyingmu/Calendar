package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;

import java.util.List;

/**
 * Created by 谢东方xdf on 2018/3/25.
 */
public class BodyPane extends StackPane {
    private static BodyPane bodyPane;
    private ContentGrid contentGrid;

    public static BodyPane getInstance() {
        if (bodyPane == null) {
            bodyPane = new BodyPane();
        }
        return bodyPane;
    }

    public BodyPane() {
        contentGrid = new ContentGrid(DateUtil.getToday());
        this.getChildren().add(contentGrid);
    }

    public void changeContent(CalendarDate date) {
        //// TODO: 2018/3/25 remain to be tested
        this.getChildren().remove(contentGrid);
        contentGrid = new ContentGrid(date);
        this.getChildren().add(contentGrid);
    }

    private class ContentGrid extends GridPane {
        public ContentGrid(CalendarDate date){
            // this
            for (int i = 0 ; i<7 ; i++){
                StackPane pane = new StackPane();
                Label label = new Label(""+ DateUtil.DayType.values()[i].getPrintMark());
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
                pane.setMaxSize(53,26);
                pane.setMinSize(53,26);
                this.add(pane,i,0);
            }
            List<CalendarDate> calendars = DateUtil.getDaysInMonth(date);
            int dayOfWeekBegin = calendars.get(0).getDayOfWeek();
            int dayOfWeekEnd = calendars.get(calendars.size()-1).getDayOfWeek();
            dayOfWeekEnd = 6 - dayOfWeekEnd;
            if (dayOfWeekEnd < 0){
                dayOfWeekEnd = 6;
            }
            int printTotal = dayOfWeekBegin + calendars.size() + dayOfWeekEnd;
            int weekNum = printTotal/7;
            for (int row = 1 ; row <= weekNum ; row++){
                for(int column = 0; column < 7; column++){
                    // // TODO: 2018/3/25 judge current day
                    StackPane pane = new StackPane();
                    int index = (row-1)*7 + column + 1 - dayOfWeekBegin - 1;
                    String labelStr = "";
                    if (index >= 0 && index < calendars.size()){
                        labelStr += calendars.get(index).getDay();
                    }
                    Label label = new Label(labelStr);
                    label.setAlignment(Pos.CENTER);
                    pane.getChildren().add(label);
                    pane.setMaxSize(53,26);
                    pane.setMinSize(53,26);
                    this.add(pane,column,row);
                }
            }
        }
    }
}
