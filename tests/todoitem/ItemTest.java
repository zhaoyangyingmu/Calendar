package todoitem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import todoitem.itemSub.AppointmentItem;
import todoitem.itemSub.MeetingItem;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Class ItemTest tests begin! Good Luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class TimeStamp tests end! Are you satisfied?");
    }

    @Test
    public void NullItemInstance() {
        TimeStamp from = new TimeStamp(2018, 5, 11, 0, 0);
        TimeStamp to = new TimeStamp(2018, 5, 10, 0, 0); //非法时间段，不应该创建待办事项实例
        Item item = null;
        try {
            item = new OtherItem(from, to, "");
        } catch (Exception e) {
            System.out.println();
        }
        assertNull(item);
    }

    @Test
    public void isDuringTime() throws Exception {
        List<TimeStamp> fromCases = new ArrayList<TimeStamp>() {
            {
                add(new TimeStamp(2016, 2, 29, 0, 0));
                add(new TimeStamp(2013, 1, 31, 16, 6));
                add(new TimeStamp(2018, 6, 30, 5, 42));
            }
        };
        List<TimeStamp> toCases = new ArrayList<TimeStamp>() {
            {
                add(new TimeStamp(2016, 2, 29, 6, 31));
                add(new TimeStamp(2014, 1, 31, 16, 6));
                add(new TimeStamp(2018, 6, 30, 5, 43));
            }
        };
        List<Item> itemsDuringThisPeriod = new ArrayList<Item>() {
            {
                add(new AppointmentItem(new TimeStamp(2016, 2, 29, 0, 1),
                        new TimeStamp(2016, 2, 29, 0, 2),
                        "", "", ""));

                add(new AppointmentItem(new TimeStamp(2013, 1, 31, 23, 1),
                        new TimeStamp(2014, 1, 31, 16, 6),
                        "", "", ""));

                add(new AppointmentItem(new TimeStamp(2018, 6, 30, 5, 42),
                        new TimeStamp(2018, 6, 30, 5, 43),
                        "", "", ""));
            }
        };
        for (int i = 0; i < fromCases.size(); i++) {
            assertTrue(itemsDuringThisPeriod.get(i).isDuringTime(fromCases.get(i), toCases.get(i)));
        }
    }

    @Test
    public void setFrom() throws Exception {
        Item item = new MeetingItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2),
                "", "", "");
        TimeStamp timeStamp = new TimeStamp(2018, 4, 14, 20, 25);
        item.setFrom(timeStamp);
        assertEquals(timeStamp, item.getFrom());
    }

    @Test
    public void setTo() throws Exception {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "");
        TimeStamp timeStamp = new TimeStamp(2018, 4, 14, 20, 25);
        item.setTo(timeStamp);
        assertEquals(timeStamp, item.getTo());
    }

    @Test
    public void setDetailText() throws Exception {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "");
        String detailText = "HANG ON!";
        item.setDetailText(detailText);
        assertEquals(detailText, item.getDetailText());
    }

    @Test
    public void setItemType() throws Exception {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "");
        Item.ItemType type = Item.ItemType.DATE;
        item.setItemType(Item.ItemType.DATE);
        assertEquals(type, item.getItemType());
    }
}