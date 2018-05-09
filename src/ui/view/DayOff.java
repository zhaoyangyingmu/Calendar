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
        dayItem.addStyleClass("day_off");
    }

    @Override
    protected void paint() {
        Label offLabel = new Label("休");
        offLabel.getStyleClass().add("day_off_label");
        //TODO label属性
        dayItem.setRightNode(offLabel);
    }

}
