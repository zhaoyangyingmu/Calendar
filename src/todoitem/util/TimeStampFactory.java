package todoitem.util;

import exception.InvalidDateException;
import kernel.CalendarDate;

public class TimeStampFactory {
    public static TimeStamp createStampDayStart(int year, int month, int day) {
        return new TimeStamp(year, month, day, 0, 0);
    }

    public static TimeStamp createStampDayEnd(int year, int month, int day) {
        return new TimeStamp(year, month, day, 23, 59);
    }

    public static TimeStamp createStampByString(String stampString) {
        String msg[] = stampString.trim().split(" ");
        try {
            CalendarDate date = new CalendarDate(msg[0]);
            String timeMsg[] = msg[1].trim().split(":");
            return new TimeStamp(date.getYear(), date.getMonth(), date.getDay(), Integer.parseInt(timeMsg[0]), Integer.parseInt(timeMsg[1]));
        } catch (InvalidDateException e) {
            return null;
        }
    }
}
