package todoitem.itemSub;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class OtherItemTest {
    @Test
    public void attrTest() throws Exception {
        List<Object []> list = Arrays.asList(new Object[][]{
                {"2018-5-20" , "2018-5-20" , "和c某某约会"} ,
                {"2018-5-12" , "2018-5-12" , "和s某某约会"},
                {"2018-5-18" , "2018-5-18" , "和m某某约会"}
        });
        for(int i = 0 ; i < list.size() ; i++) {
            CalendarDate fromCal = new CalendarDate((String)list.get(i)[0]);
            TimeStamp from = TimeStamp.createStampDayStart(fromCal.getYear(), fromCal.getMonth(), fromCal.getDay());
            CalendarDate toCal = new CalendarDate((String)list.get(i)[1]);
            TimeStamp to = TimeStamp.createStampDayEnd(toCal.getYear(),toCal.getMonth(), toCal.getDay());
            OtherItem tmpItem = new OtherItem(from , to , (String)list.get(i)[2]);
            assertEquals(tmpItem.getDetailText(),(String)list.get(i)[2]);

        }
    }
}