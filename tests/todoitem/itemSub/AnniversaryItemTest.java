package todoitem.itemSub;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AnniversaryItemTest {
    @Test
    public void attrTest() throws Exception {
        List<Object[]> list = Arrays.asList(new Object[][] {
                {"2018-7-6","2018-9-1","总算放假了！" , "放假纪念日"},
                {"2025-10-1","2028-9-1","啊！结婚了！呵呵！" , "结婚纪念日"},
                {"2099-10-3","2099-10-4","我去世了！呵呵！" , "逝世纪念日"},
                {"2018-9-10","2029-10-4","灾难性事件： 开学了，，，" , "灾难纪念日"}
        });
        for(int i = 0 ; i < list.size() ; i++) {
            CalendarDate fromCal = new CalendarDate((String)list.get(i)[0]);
            TimeStamp from = TimeStamp.createStampDayStart(fromCal.getYear(), fromCal.getMonth(), fromCal.getDay());
            CalendarDate toCal = new CalendarDate((String)list.get(i)[1]);
            TimeStamp to = TimeStamp.createStampDayEnd(toCal.getYear(),toCal.getMonth(),toCal.getDay());
            Item tmpItem = new AnniversaryItem(from , to , (String)list.get(i)[2] , (String)list.get(i)[3]);
            assertEquals(((AnniversaryItem) tmpItem).getAnniversaryType(), list.get(i)[3]);
            assertEquals(((AnniversaryItem) tmpItem).getStartDay(), from);
        }
    }
}