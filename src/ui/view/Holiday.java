package ui.view;


import javafx.scene.control.Label;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */

/*节日*/
public class Holiday extends Special {
    private String holiday;

    public Holiday(DayItem item, String holiday) {
        super(item);
        this.holiday = holiday;
        init();
    }

    @Override
    protected void init() {
        setStyleClass();
        paint();
    }

    @Override
    protected void setStyleClass() {
        this.getStyleClass().add("holiday");
    }

    @Override
    protected void paint() {
        Label holidayLabel = new Label(holiday);
        //TODO label属性
        this.setBottom(holidayLabel);

    }
}
