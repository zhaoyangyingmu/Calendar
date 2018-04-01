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
    private ActivityType activityType;
    private String message;

    public CalendarDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        activityType = ActivityType.LEISURE;
        message = "";
    }

    /**
     * a constructor that can return a kernel.CalendarDate object through the given string.
     * @param dateString format: 2018-3-18
     */
    /**
     * Before using this constructor, one must make sure dateString is correct.
     */
    public CalendarDate(String dateString) throws InvalidDateException {
        if (!DateUtil.isFormatted(dateString)){
            throw new InvalidDateException("Date format is invalid!");
        }
        String[] date_parts = dateString.split("-");
        year = Integer.parseInt(date_parts[0]);
        month = Integer.parseInt(date_parts[1]);
        day = Integer.parseInt(date_parts[2]);
        activityType = ActivityType.LEISURE;
        message = "";
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
     * <p>
     * Don't use the existing implement like Calendar.setTime(),
     * try to implement your own algorithm.
     *
     * @return 1-7, 1 stands for Monday and 7 stands for Sunday
     */
    public int getDayOfWeek() {
        if (!DateUtil.isValid(this)){
            return -1;
        }
        int C = year / 100;
        int y = year % 100;
        int m = month;
        if (month <= 2) {
            m = month + 12;
        }
        int d = day;
        int dayOfWeek = C / 4 - 2 * C + y + y / 4 + ((26 * (m + 1)) / 10) + d - 1;
        dayOfWeek = ((dayOfWeek % 7) + 7) % 7;
        if (dayOfWeek == 0) {
            return 7;
        }
        return dayOfWeek;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType){
        this.activityType = activityType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "" + year + "-" + month + "-" + day;
    }

    public boolean equals(CalendarDate date) {
        if ((this.getYear() == date.getYear()) && (this.getMonth() == date.getMonth()) && (this.getDay() == date.getDay())) {
            return true;
        }
        return false;
    }

    public enum ActivityType {
        LEISURE("leisure") , DATING("dating") , STUDY("study");
        private String typeStr;
        ActivityType(String typeStr) {
            this.typeStr = typeStr;
        }
        public String getTypeStr() {
            return typeStr;
        }
    }

}
