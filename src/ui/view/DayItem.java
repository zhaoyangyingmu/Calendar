package ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import kernel.CalendarDate;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */
public abstract class DayItem extends BorderPane {
    private CalendarDate date;
    private volatile Label dayLabel;

    public void setDate(CalendarDate d) {
        this.date = d;
        if (this.dayLabel == null)
            this.dayLabel = new Label();
        this.dayLabel.setText(d.getDay() + "");
        this.setCenter(dayLabel);
    }

    public CalendarDate getDate() {
        return this.date;
    }

    public void addStyleClass(String... classNames) {
        for (String name : classNames) {
            if (!this.getStyleClass().contains(name))
                this.getStyleClass().add(name);
        }
    }

    public void removeStyleClass(String... classNames) {
        for (String name : classNames) {
            while (this.getStyleClass().contains(name))
                this.getStyleClass().remove(name);
        }
    }
}
