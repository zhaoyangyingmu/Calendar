package todoitem.itemSub;

import exception.InvalidDateException;
import kernel.CalendarDate;
import org.junit.Test;
import todoitem.util.TimeStamp;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InterviewItemTest {
    @Test
    public void attrTest() throws Exception {
        List<Object []> list = Arrays.asList(new Object[][] {
                {"2018-1-24", "2018-1-24", "张江高科", "张江大学股份有限公司", "程序员", "必得工作！"},
                {"2018-1-28", "2018-1-28", "五角场", "万达股份有限公司", "总经理", "值得一试！"},
                {"2018-1-29", "2018-1-29", "北京中关村", "中关村股份有限公司", "架构师", "好好努力！"},
        });
        for (int i = 0 ; i < list.size() ; i++) {
            CalendarDate fromCal = new CalendarDate((String)list.get(i)[0]);
            TimeStamp from = TimeStamp.createStampDayStart(fromCal.getYear(), fromCal.getMonth(), fromCal.getDay());
            CalendarDate toCal = new CalendarDate((String)list.get(i)[1]);
            TimeStamp to = TimeStamp.createStampDayEnd(toCal.getYear(),toCal.getMonth(), toCal.getDay());
            InterviewItem tmpItem = new InterviewItem(from , to , (String)list.get(i)[2], (String)list.get(i)[3],
                    (String)list.get(i)[4], (String)list.get(i)[5]);
            assertEquals(tmpItem.getPlace() , (String)list.get(i)[2]);
            assertEquals(tmpItem.getCompany(), (String)list.get(i)[3]);
            assertEquals(tmpItem.getJob() , (String)list.get(i)[4]);
            assertEquals(tmpItem.getRemark(), (String)list.get(i)[5]);
        }

    }
}