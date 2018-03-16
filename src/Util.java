/*
* This class provides three methods to get current year, month and day of month.
* Don't modify this class!!
* */
import java.util.Calendar;

public class Util {
    private static Calendar calendar = Calendar.getInstance();
    public static int getCurrentYear(){
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth(){
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

}

