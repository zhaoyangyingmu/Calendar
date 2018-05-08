package ui.view;

import kernel.CalendarDate;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */

/*寻常的一天，即无待办事项、非节日、非假日*/
public class OrdinaryDay extends DayItem {
    public OrdinaryDay(CalendarDate d) {
        setDate(d);
    }
}
