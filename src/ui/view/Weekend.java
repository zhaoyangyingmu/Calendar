package ui.view;

public class Weekend extends Special {

    Weekend(DayItem item) {
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
        dayItem.addStyleClass("weekend");
    }

    @Override
    protected void paint() {

    }
}
