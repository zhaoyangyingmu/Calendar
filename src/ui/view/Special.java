package ui.view;


/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */


abstract class Special extends DayItem {
    protected DayItem dayItem;

    Special(DayItem item) {
        this.dayItem = item;
    }

    @Override
    public DayItem getItem() {
        return dayItem.getItem();
    }
}
