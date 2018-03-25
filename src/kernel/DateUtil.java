package kernel;/*
* This class provides some utils that may help you to finish this lab.
* getToday() is finished, you can use this method to get the current date.
* The other four methods getDaysInMonth(), isValid(), isFormatted() and isLeapYear() are not finished,
* you should implement them before you use.
*
* */

import kernel.CalendarDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
    /**
     * get a kernel.CalendarDate instance point to today
     *
     * @return a kernel.CalendarDate object
     */
    public static CalendarDate getToday() {
        Calendar calendar = Calendar.getInstance();
        return new CalendarDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * get all dates in the same month with given date
     *
     * @param date the given date
     * @return a list of days in a whole month
     */
    public static List<CalendarDate> getDaysInMonth(CalendarDate date) {
        if (date == null) {
            return null;
        }
        if (!isValid(date)) {
            return null;
        }
        int num = getNumOfDays(date);
        int current = date.getDay();
        List<CalendarDate> calendarList = new ArrayList<CalendarDate>();
        for (int i = 1; i <= num; i++) {
            if (i == current) {
                calendarList.add(date);
                continue;
            }
            calendarList.add(new CalendarDate(date.getYear(), date.getMonth(), i));
        }
        return calendarList;
    }

    public static int getNumOfDays(CalendarDate date) {
        MonthType monthType = MonthType.values()[date.getMonth()];
        if ((isLeapYear(date.getYear())) && (monthType == MonthType.FEB)) {
            return 29;
        }
        return monthType.getDays();
    }

    /**
     * Judge whether the input date is valid. For example, 2018-2-31 is not valid
     *
     * @param date the input date
     * @return true if the date is valid, false if the date is not valid.
     */
    public static boolean isValid(CalendarDate date) {
        if ((date.getYear() < 1800) || (date.getYear() > 2100)) {
            return false;
        }
        if ((date.getMonth() < 1) || (date.getMonth() > 12)) {
            return false;
        }
        MonthType monthType = MonthType.values()[date.getMonth()];
        if ((isLeapYear(date.getYear())) && (monthType == MonthType.FEB)) {
            if (date.getDay() <= 29) {
                return true;
            } else {
                return false;
            }
        }

        if ((date.getDay() <= monthType.getDays()) && (date.getDay() > 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Judge whether the input is formatted.
     * For example, 2018/2/1 is not valid and 2018-2-1 is valid.
     *
     * @param dateString
     * @return true if the input is formatted, false if the input is not formatted.
     */
    public static boolean isFormatted(String dateString) {
        String[] date_parts = dateString.split("-");
        if (date_parts.length == 3) {
            try {
                int year = Integer.parseInt(date_parts[0]);
                int month = Integer.parseInt(date_parts[1]);
                int day = Integer.parseInt(date_parts[2]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Judge whether the input year is a leap year or not.
     * For example, year 2000 is a leap year, and 1900 is not.
     *
     * @param year
     * @return true if the input year is a leap year, false if the input is not.
     */
    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0) && (year % 100 != 0)) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        }
        return false;
    }

    public enum MonthType {
        NULL(0),
        JAN(31), FEB(28), MAR(31), APR(30),
        MAY(31), JUN(30), JUL(31), AUG(31),
        SEPT(30), OCT(31), NOV(30), DEC(31);
        private final int days;

        MonthType(int days) {
            this.days = days;
        }

        public int getDays() {
            return days;
        }
    }

    public enum DayType {
        SUN("日"), MON("一"), TUES("二"), WEDN("三"),
        THUR("四"), FRI("五"), SAT("六");
        private final String printMark;

        DayType(String printMark) {
            this.printMark = printMark;
        }
        public String getPrintMark(){
            return printMark;
        }

    }

}

