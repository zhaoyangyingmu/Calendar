package ui.view;


/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */


abstract class Special extends DayItem {
    private DayItem dayItem;

    Special(DayItem item) {
        this.dayItem = item;
    }

    protected abstract void init();

    protected abstract void setStyleClass();

    protected abstract void paint();

}
