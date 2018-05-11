package todoitem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import todoitem.itemSub.Appointment;
import todoitem.itemSub.Meeting;
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
        Item item = new OtherItem(from, to, "", Item.ItemType.LEISURE);
        assertNull(item);
    }

    @Test
    public void isDuringTime() {
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
                add(new Appointment(new TimeStamp(2016, 2, 29, 0, 1),
                        new TimeStamp(2016, 2, 29, 0, 2),
                        "", Item.ItemType.LEISURE, "", ""));

                add(new Appointment(new TimeStamp(2013, 1, 31, 23, 1),
                        new TimeStamp(2014, 1, 31, 16, 6),
                        "", Item.ItemType.LEISURE, "", ""));

                add(new Appointment(new TimeStamp(2018, 6, 30, 5, 42),
                        new TimeStamp(2018, 6, 30, 5, 43),
                        "", Item.ItemType.LEISURE, "", ""));
            }
        };
        for (int i = 0; i < fromCases.size(); i++) {
            assertTrue(itemsDuringThisPeriod.get(i).isDuringTime(fromCases.get(i), toCases.get(i)));
        }

    }

    @Test
    public void setFrom() {
        Item item = new Meeting(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2),
                "", Item.ItemType.LEISURE, "", "");
        TimeStamp timeStamp = new TimeStamp(2018, 4, 14, 20, 25);
        item.setFrom(timeStamp);
        assertEquals(timeStamp, item.getFrom());
    }

    @Test
    public void setTo() {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "", Item.ItemType.LEISURE);
        TimeStamp timeStamp = new TimeStamp(2018, 4, 14, 20, 25);
        item.setTo(timeStamp);
        assertEquals(timeStamp, item.getTo());
    }

    @Test
    public void setDetailText() {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "", Item.ItemType.LEISURE);
        String detailText = "HANG ON!";
        item.setDetailText(detailText);
        assertEquals(detailText, item.getDetailText());
    }

    @Test
    public void setItemType() {
        Item item = new OtherItem(new TimeStamp(2016, 2, 29, 0, 1),
                new TimeStamp(2016, 2, 29, 0, 2), "", Item.ItemType.LEISURE);
        Item.ItemType type = Item.ItemType.DATING;
        item.setItemType(Item.ItemType.DATING);
        assertEquals(type, item.getItemType());
    }
}