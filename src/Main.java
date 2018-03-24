import exception.InvalidDateException;

import java.util.List;

/*
* Start here!
*
* */
public class Main {
    public static void main(String[] args) {
        //todo
        try {
            CalendarDate c = new CalendarDate("2001-" + 10 + "-" + "1");
            System.out.println("Today is " + c.getDayOfWeek());
        } catch (InvalidDateException e) {
            System.out.println(e.toString());
        }
    }
}
