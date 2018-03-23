import exception.InvalidDateException;

import java.util.List;

/*
* Start here!
*
* */
public class Main {
    public static void main(String[] args) {

        System.out.println(DateUtil.getToday().toString());
        //todo
        try {
            CalendarDate c = new CalendarDate("2000-" + 2 + "-" + "1");
            List<CalendarDate> list = DateUtil.getDaysInMonth(c);
            for (int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }
        } catch (InvalidDateException e) {
            System.out.println(e.toString());
        }
    }
}
