package todoitem.util;

public class TimeStampFactory {
    public static TimeStamp createStampDayStart(int year, int month , int day) {
        return new TimeStamp(year, month , day , 0 , 0);
    }

    public static TimeStamp createStampDayEnd(int year, int month , int day) {
        return new TimeStamp(year, month , day , 23 , 59);
    }
}
