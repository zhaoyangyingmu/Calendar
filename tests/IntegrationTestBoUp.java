import kernel.CalendarDateTest;
import kernel.DateUtilTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import todoitem.Item;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class IntegrationTestBoUp {
    @Parameterized.Parameters
    public static Collection<Object []> getTestData() {
        return Arrays.asList(new Object[][]{
                {"2018-5-1", "2018-4-29", false, false, false, Item.ItemType.DATE},     //非法时间段，不能创建待办事项对象
//                {"2018-1-1", "2017-12-30", false, false, false, Item.ItemType.DATE},    //非法时间段
//                {"2016-2-30", "2016-3-1", true, false, false, Item.ItemType.DATE},      //非法日期
//                {"2000-2-29", "2018-2-29", false, true, false, Item.ItemType.DATE},     //非法日期
//                {"2020-2-29", "2020-3-0", false, true, false, Item.ItemType.DATE},      //非法日期
//                {"0000-00-00", "2018-5-12", true, false, false, Item.ItemType.DATE},    //非法日期
//                {"2018-2-29", "2018-4-29", false, false, false, Item.ItemType.DATE},    //非法日期
//                {"2018-2-28", "2018-3-1", false, false, true, Item.ItemType.DATE},
//                {"2000-2-29", "2018-2-28", false, false, true, Item.ItemType.CUSTOM},
//                {"2020-2-29", "2028-4-1", false, false, true, Item.ItemType.MEETING},
//                {"2018-5-10", "2018-5-12", false, false, true, Item.ItemType.DATE},
//                {"2018-5-9", "2018-5-11", false, false, true, Item.ItemType.CUSTOM},
//                {"2018-5-10", "2018-5-12", false, false, true, Item.ItemType.MEETING},
        });
    }

    public IntegrationTestBoUp(String startTime, String endTime, boolean a , boolean b , boolean c, Item.ItemType type) {

    }

    private void calendarTest() {
        CalendarDateTest test = new CalendarDateTest();
        test.getDayOfWeekTrue();
        test.testGetDayOfWeekFalse();
    }

    private void dateUtilTest() {
        DateUtilTest utilTest = new DateUtilTest();
        utilTest.getDaysInMonthNotNull();
        utilTest.getDaysInMonthNull();
        utilTest.testGetDaysInMonthIllegal();
        utilTest.Integration();
        utilTest.testIsValidNull();
        utilTest.testIsValidTrue();
        utilTest.testIsValidFalse();
        utilTest.testIsFormattedNull();
        utilTest.testIsFormattedFalse();
        utilTest.testIsFormattedTrue();
        utilTest.testIsLeapYearFalse();
        utilTest.testIsLeapYearTrue();
    }

    private void itemTest() {

    }


    @Test
    public void integration() {
        /**
         *  calendar 和 dateUitl 的测试
         * */
        calendarTest();
        dateUtilTest();

        /**
         * 接下来测试 todoitem 包
         * */

    }

}
