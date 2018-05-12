package holiday;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ui.view.DayItem;
import ui.view.DayOff;
import ui.view.Festival;
import ui.view.OrdinaryDay;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DayManagerTest {
    private String dateString;
    private String expectedFestival;
    private String expectedWorkday;
    private String expectedRest;

    public DayManagerTest(String dateString, String festival, String workday, String rest) {
        this.dateString = dateString;
        this.expectedFestival = festival;
        this.expectedWorkday = workday;
        this.expectedRest = rest;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"2018-01-01", "元旦", null, "休"},
                {"2018-02-16", "春节", null, "休"},
                {"2018-04-05", "清明节", null, "休"},
                {"2018-05-01", "劳动节", null, "休"},
                {"2018-06-18", "端午节", null, "休"},
                {"2018-08-15", "中秋节", null, "休"},
                {"2018-10-01", "国庆节", null, "休"},
                {"2018-02-15", null, null, "休"},
                {"2018-02-21", null, null, "休"},
                {"2018-04-07", null, null, "休"},
                {"2018-04-29", null, null, "休"},
                {"2018-06-16", null, null, "休"},
                {"2018-09-22", null, null, "休"},
                {"2018-10-07", null, null, "休"},
                {"2018-02-11", null, "班", null},
                {"2018-02-24", null, "班", null},
                {"2018-04-08", null, "班", null},
                {"2018-04-28", null, "班", null},
                {"2018-09-29", null, "班", null},
                {"2018-09-30", null, "班", null},
                {"2018-05-10", null, null, null},
                {"2018-10-10", null, null, null},
                {"2018-11-11", null, null, null},
                {"2018-12-12", null, null, null},
        });
    }

    @Test
    public void isFestival() throws Exception {
        DayType type = DayManager.isFestival(dateString);
        if (expectedFestival == null) {
            assertNull(type);
        } else {
            assertNotNull(type);
            assertEquals(type.getName(), expectedFestival);
        }
    }

    @Test
    public void isWorkDay() throws Exception {
        DayType type = DayManager.isWorkDay(dateString);
        if (expectedWorkday == null) {
            assertNull(type);
        } else {
            assertNotNull(type);
            assertEquals(type.getName(), expectedWorkday);
        }
    }

    @Test
    public void isRest() throws Exception {
        DayType type = DayManager.isRest(dateString);
        if (expectedRest == null) {
            assertNull(type);
        } else {
            assertNotNull(type);
            assertEquals(type.getName(), expectedRest);
        }
    }

    @Test
    public void judgeDays() throws Exception {
        isFestival();
        isRest();
        isWorkDay();
    }

    @Test
    public void holidayDetail() throws Exception {
        Holiday holiday = DayManager.holidayDetail(dateString);
        if (expectedFestival == null)
            assertNull(holiday);
        else {
            assertNotNull(holiday);
            assertEquals(holiday.getZh_name(), expectedFestival);
        }
    }


    @Test
    public void integrationTestHoliday() {
        try {
            DayItem item = new OrdinaryDay(new CalendarDate(dateString));
            DayType type;
            if ((type = DayManager.isFestival(dateString)) != null) {
                item = new Festival(item, type.getName()).getItem(); //获取节日名称
            }
            if ((type = DayManager.isRest(dateString)) != null
                    || (type = DayManager.isWorkDay(dateString)) != null)
                item = new DayOff(item, type.getName()).getItem();
            if (expectedFestival == null)
                assertFalse(item instanceof Festival);
            if (expectedRest == null && expectedWorkday == null)
                assertFalse(item instanceof DayOff);
        } catch (InvalidDateException e) {
            e.printStackTrace();
        }
    }
}