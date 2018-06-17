package todoitem.itemSub;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import todoitem.Item;
import todoitem.util.TimeStamp;
import todoitem.util.TimeStampFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AppointmentItemTest {
    @Test
    public void attrTest() throws Exception {
        List<Object []> list = Arrays.asList(new Object[][]{
                {"2018-5-20" , "2018-5-20" , "和c某某约会" , "c某某" , "某影院"} ,
                {"2018-5-12" , "2018-5-12" , "和s某某约会" , "s某某" , "某大型商场"},
                {"2018-5-18" , "2018-5-18" , "和m某某约会" , "m某某" , "某大型广场"}
        });
        for(int i = 0 ; i < list.size() ; i++) {
            CalendarDate fromCal = new CalendarDate((String)list.get(i)[0]);
            TimeStamp from = TimeStampFactory.createStampDayStart(fromCal.getYear(), fromCal.getMonth(), fromCal.getDay());
            CalendarDate toCal = new CalendarDate((String)list.get(i)[1]);
            TimeStamp to = TimeStampFactory.createStampDayEnd(toCal.getYear(),toCal.getMonth(), toCal.getDay());
            Item tmpItem = new AppointmentItem(from , to , (String)list.get(i)[2],(String)list.get(i)[3] , (String)list.get(i)[4]);
            assertEquals(tmpItem.getDetailText(),(String)list.get(i)[2]);
            assertEquals(((AppointmentItem) tmpItem).getParticipants() , (String)list.get(i)[3]);
            assertEquals(((AppointmentItem) tmpItem).getLocation(), (String)list.get(i)[4]);
        }
    }

}