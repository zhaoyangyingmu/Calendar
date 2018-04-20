package ui.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import kernel.CalendarDate;
import kernel.DateUtil;
import kernel.Display;
import ui.Config;

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
            bodyPane.getStylesheets().add(Config.class.getResource("/stylesheet/body.css").toString());
            bodyPane.getStyleClass().add("bodyPane");
        }
        return bodyPane;
    }

    public BodyPane() {
        contentGrid = new ContentGrid(DateUtil.getToday());
        this.getChildren().add(contentGrid);
    }

    public void changeContent(CalendarDate date) {
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
                label.setStyle("-fx-text-fill: white;");
                label.setAlignment(Pos.CENTER);
                pane.getChildren().add(label);
                pane.setMaxSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                pane.setMinSize(Config.getRectangleWidth(),Config.getRectangleHeight());
                pane.getStyleClass().add("thead");
                this.add(pane,i,0);
            }
            List<CalendarDate> calendars = DateUtil.getDaysInMonth(date);
            int dayOfWeekBegin = calendars.get(0).getDayOfWeek()%7;
            int dayOfWeekEnd = calendars.get(calendars.size()-1).getDayOfWeek();
            dayOfWeekEnd = 6 - dayOfWeekEnd;
            if (dayOfWeekEnd < 0){
                dayOfWeekEnd = 6;
            }
            int printTotal = dayOfWeekBegin + calendars.size() + dayOfWeekEnd;
            int weekNum = printTotal/7;
            for (int row = 1 ; row <= weekNum ; row++){
                for(int column = 0; column < 7; column++){
                    StackPane pane = new StackPane();
                    int index = (row-1)*7 + column + 1 - dayOfWeekBegin - 1;
                    String labelStr = "";
                    boolean isToday = false;
                    if (index >= 0 && index < calendars.size()){
                        labelStr += calendars.get(index).getDay();
//                        CalendarDate.ActivityType activityType = calendars.get(index).getActivityType();
//                        if (activityType == CalendarDate.ActivityType.LEISURE){
//                            pane.getStyleClass().add("leisure");
//                        }else if (activityType == CalendarDate.ActivityType.DATING){
//                            pane.getStyleClass().add("dating");
//                        }else {
//                            pane.getStyleClass().add("study");
//                        }
//                        if (calendars.get(index).equals(DateUtil.getToday())){
//                            isToday = true;
//                        }
                        pane.setOnMouseClicked(event -> {
                            try {
                                Display.addEdit(calendars.get(index));
                            }catch (Exception e){
                                System.out.println("Can't click twice!");
                            }
                        });
                    }else {
                        pane.getStyleClass().add("leisure");
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
    }
}
