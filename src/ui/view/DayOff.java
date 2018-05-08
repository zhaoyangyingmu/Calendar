package ui.view;

import javafx.scene.control.Label;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */

/*假日、休息日*/
public class DayOff extends Special {

    public DayOff(DayItem item) {
        super(item);
        init();
    }

    @Override
    protected void init() {
        setStyleClass();
        paint();
    }

    @Override
    protected void setStyleClass() {
        this.getStyleClass().add("day_off");
    }

    @Override
    protected void paint() {
        Label offLabel = new Label();
        //TODO label属性
        this.setLeft(offLabel);
    }

}
