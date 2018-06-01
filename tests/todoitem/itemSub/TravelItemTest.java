package todoitem.itemSub;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import todoitem.util.TimeStamp;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TravelItemTest {
    @Test
    public void attrTest() throws Exception {
        List<Object []> list = Arrays.asList(new Object[][] {
                {"2018-1-3", "2019-1-2", "plane", "北京", "20", "北京一游"},
                {"2018-1-24", "2018-1-25", "train", "南京", "30", "南京一日游"},
                {"2018-3-5", "2018-3-7", "boat", "上海", "30", "上海两日游"},
        });
        for (int i = 0 ; i < list.size() ; i++) {
            CalendarDate fromCal = new CalendarDate((String)list.get(i)[0]);
            TimeStamp from = TimeStamp.createStampDayStart(fromCal.getYear(), fromCal.getMonth(), fromCal.getDay());
            CalendarDate toCal = new CalendarDate((String)list.get(i)[1]);
            TimeStamp to = TimeStamp.createStampDayEnd(toCal.getYear(),toCal.getMonth(), toCal.getDay());
            TravelItem tmpItem = new TravelItem(from , to , (String)list.get(i)[2], (String)list.get(i)[3],
                    (String)list.get(i)[4], (String)list.get(i)[5]);
            assertEquals(tmpItem.getWay() , (String)list.get(i)[2]);
            assertEquals(tmpItem.getPlace(), (String)list.get(i)[3]);
            assertEquals(tmpItem.getNumber() , (String)list.get(i)[4]);
            assertEquals(tmpItem.getRemark(), (String)list.get(i)[5]);
        }

    }

}