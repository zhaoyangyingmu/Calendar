package todoitem.util;

import java.sql.Timestamp;

public class TimeStamp {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    public TimeStamp(int year, int month , int day , int hour,int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public void changeTo(int year, int month , int day , int hour , int minute){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public boolean isAfter(Timestamp target){
        return false;
    }

    public boolean isBefore(TimeStamp target){
        return false;
    }
}
