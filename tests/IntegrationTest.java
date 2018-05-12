import exception.InvalidDateException;
import io.ItemIO;
import kernel.CalendarDate;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.AppointmentItem;
import todoitem.itemSub.MeetingItem;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * # 集成测试，围绕待办事项测试
 * 一、信息输入（ --> 待办事项）时间字符串 =》 CalendarDate对象 =》 TimeStamp对象 =》 待办事项实例 =》 检查时间
 * 1.时间选择
 * - 起始时间    （字符串）
 * - 截止时间    （字符串）
 * 2.类型选择
 * - 会议
 * - 约会
 * - 其他
 * 3.详细信息
 * 4，得到待办事项对象，检查待办事项属性（Test)
 * 5.存入
 * 二、数据库（ --> 待办事项）数据库 =》 Item(待办事项)对象 =》 检查待办事项 =》 TimeStamp => CalendarDate对象 =》 检查时间
 * 1.读取
 * 2.读取时间
 * - 检查起始时间   (Test)
 * - 检查截止时间   (Test)
 * 3.读取类型
 * - 检查待办事项类型 (Test)
 * 4.读取详细信息
 */
@RunWith(Parameterized.class)
public class IntegrationTest {
    private final static ArrayList<Item> expectedItems = new ArrayList<>();

    private String frDateString;
    private String toDateString;
    private boolean frDateIsNull;
    private boolean toDateIsNull;
    private boolean isDuringTime;
    private Item.ItemType type;

    public IntegrationTest(String frDateString, String toDateString,
                           boolean frDateIsNull, boolean toDateIsNull,
                           boolean isDuringTime, Item.ItemType type) {
        this.frDateString = frDateString;
        this.toDateString = toDateString;
        this.frDateIsNull = frDateIsNull;
        this.toDateIsNull = toDateIsNull;
        this.isDuringTime = isDuringTime;
        this.type = type;
    }

    @BeforeClass
    public static void delete() {
        File file = new File("output.txt");
        if (file.exists())
            file.delete();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"2018-5-1", "2018-4-29", false, false, false, Item.ItemType.APPOINTMENT},     //非法时间段，不能创建待办事项对象
                {"2018-1-1", "2017-12-30", false, false, false, Item.ItemType.APPOINTMENT},    //非法时间段
                {"2016-2-30", "2016-3-1", true, false, false, Item.ItemType.APPOINTMENT},      //非法日期
                {"2000-2-29", "2018-2-29", false, true, false, Item.ItemType.APPOINTMENT},     //非法日期
                {"2020-2-29", "2020-3-0", false, true, false, Item.ItemType.APPOINTMENT},      //非法日期
                {"0000-00-00", "2018-5-12", true, false, false, Item.ItemType.APPOINTMENT},    //非法日期
                {"2018-2-29", "2018-4-29", false, false, false, Item.ItemType.APPOINTMENT},    //非法日期
                {"2018-2-28", "2018-3-1", false, false, true, Item.ItemType.APPOINTMENT},
                {"2000-2-29", "2018-2-28", false, false, true, Item.ItemType.OTHER},
                {"2020-2-29", "2028-4-1", false, false, true, Item.ItemType.MEETING},
                {"2018-5-10", "2018-5-12", false, false, true, Item.ItemType.APPOINTMENT},
                {"2018-5-9", "2018-5-11", false, false, true, Item.ItemType.OTHER},
                {"2018-5-10", "2018-5-12", false, false, true, Item.ItemType.MEETING},
        });
    }

    @Test
    public void ItemTest() {
        CalendarDate frDate = null;
        CalendarDate toDate = null;
        TimeStamp frTime = null;
        TimeStamp toTime = null;
        try {
            frDate = new CalendarDate(frDateString);
            frTime = new TimeStamp(frDate.getYear(), frDate.getMonth(), frDate.getDay(), 0, 0);
        } catch (InvalidDateException e) {
            assertTrue(frDateIsNull);
            assertNull(frDate);
        }
        try {
            toDate = new CalendarDate(toDateString);
            toTime = new TimeStamp(toDate.getYear(), toDate.getMonth(), toDate.getDay(), 23, 59);
        } catch (InvalidDateException e) {
            assertTrue(toDateIsNull);
            assertNull(toDate);
        }
        Item item = null;
        try {
            if (type == Item.ItemType.APPOINTMENT) {
                item = new AppointmentItem(frTime, toTime, "", "", "");
            } else if (type == Item.ItemType.MEETING)
                item = new MeetingItem(frTime, toTime, "", "", "");
            else
                item = new OtherItem(frTime, toTime, "");
        } catch (Exception e) {
            assertNull(item);
        }

        if (isDuringTime) {
            TimeStamp actualFrTime = item.getFrom();
            TimeStamp actualToTime = item.getTo();
            CalendarDate actualFrDate = new CalendarDate(actualFrTime.getYear(), actualFrTime.getMonth(), actualFrTime.getDay());
            CalendarDate actualToDate = new CalendarDate(actualToTime.getYear(), actualToTime.getMonth(), actualToTime.getDay());
            assertEquals(frTime, actualFrTime);
            assertEquals(toTime, actualToTime);
            assertEquals(type, item.getItemType());
            assertEquals("", item.getDetailText());
            assertEquals(frDate, actualFrDate);
            assertEquals(toDate, actualToDate);

            expectedItems.add(item);
            //存入数据库
            ItemManager manager = ItemManager.getInstance();
            manager.addItem(item);
            ItemIO.output();
        } else {
            assertNull(item);
        }

    }

    //    @Ignore
}
