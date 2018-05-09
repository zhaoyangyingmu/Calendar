package todoitem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import todoitem.itemSub.OtherMemo;
import todoitem.util.TimeStamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MemoTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Class MemoTest tests begin! Good Luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class TimeStamp tests end! Are you satisfied?");
    }

    @Test
    public void isDuringTime() {
        List<TimeStamp> fromCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2016, 2,29, 0 , 0));
                add(new TimeStamp(2013, 1,31, 16 , 6));
                add(new TimeStamp(2018, 6,30, 5 , 42));
            }
        };
        List<TimeStamp> toCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2016, 2,29, 6 , 31));
                add(new TimeStamp(2014, 1,31, 16 , 6));
                add(new TimeStamp(2018, 6,30, 5 , 43));
            }
        };
        List<OtherMemo> itemsDuringThisPeriod = new ArrayList<OtherMemo>() {
            {
                add(new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                             new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE));

                add(new OtherMemo(new TimeStamp(2013, 1,31, 23 , 1),
                        new TimeStamp(2014, 1,31, 16 , 6), "", OtherMemo.ItemType.LEISURE));

                add(new OtherMemo(new TimeStamp(2018, 6,30, 5 , 42),
                        new TimeStamp(2018, 6,30, 5 , 43), "", OtherMemo.ItemType.LEISURE));
            }
        };
        for (int i = 0; i < fromCases.size(); i++) {
            assertTrue(itemsDuringThisPeriod.get(i).isDuringTime(fromCases.get(i),toCases.get(i)));
        }
    }

    @Test
    public void setFrom() {
        OtherMemo memo = new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE);
        TimeStamp timeStamp = new TimeStamp(2018,4,14,20,25);
        memo.setFrom(timeStamp);
        assertEquals(timeStamp, memo.getFrom());
    }

    @Test
    public void setTo() {
        OtherMemo memo = new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE);
        TimeStamp timeStamp = new TimeStamp(2018,4,14,20,25);
        memo.setTo(timeStamp);
        assertEquals(timeStamp, memo.getTo());
    }

    @Test
    public void setDetailText() {
        OtherMemo memo = new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE);
        String detailText = "HANG ON!";
        memo.setDetailText(detailText);
        assertEquals(detailText, memo.getDetailText());
    }

    @Test
    public void setItemType() {
        OtherMemo memo = new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE);
        OtherMemo.ItemType type = OtherMemo.ItemType.DATING;
        memo.setItemType(OtherMemo.ItemType.DATING);
        assertEquals(type, memo.getItemType());
    }
}