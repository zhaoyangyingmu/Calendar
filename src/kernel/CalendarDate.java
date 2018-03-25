package kernel;

import exception.InvalidDateException;

/**
 * We have finished part of this class yet, you should finish the rest.
 * 1. A constructor that can return a kernel.CalendarDate object through the given string.
 * 2. A method named getDayOfWeek() that can get the index of a day in a week.
 */
public class CalendarDate {
    private final int year;
    private final int month;
    private final int day;

    public CalendarDate(int year, int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * a constructor that can return a kernel.CalendarDate object through the given string.
     * @param dateString format: 2018-3-18
     */
    public CalendarDate(String dateString) throws InvalidDateException {
        if (DateUtil.isFormatted(dateString)){
            String[] date_parts = dateString.split("-");
            year = Integer.parseInt(date_parts[0]);
            month = Integer.parseInt(date_parts[1]);
            day = Integer.parseInt(date_parts[2]);
        }else{
            throw new InvalidDateException(dateString);
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    /**
     * Get index of the day in a week for one date.
     *
     * Don't use the existing implement like Calendar.setTime(),
     * try to implement your own algorithm.
     * @return 1-7, 1 stands for Monday and 7 stands for Sunday
     */
    public int getDayOfWeek(){
        int C = year / 100;
        int y = year % 100;
        int m = month;
        if (month <= 2){
            m = month + 12;
        }
        int d = day;
        int dayOfWeek = C/4 - 2*C + y + y/4  + ((26*(m+1))/10) + d - 1;
        dayOfWeek = ((dayOfWeek % 7) + 7) % 7;
        if (dayOfWeek == 0){
            return 7;
        }
        return dayOfWeek;
    }

    @Override
    public String toString(){
        return ""+year+"-"+month+"-"+day;
    }

}
