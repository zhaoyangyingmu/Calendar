import holiday.DayManagerTest;
import kernel.CalendarDateTest;
import kernel.DateUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import todoitem.ItemManagerTest;
import todoitem.ItemTest;
import todoitem.util.TimeStampTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({IntegrationTestIO.class,TimeStampTest.class, ItemTest.class , ItemManagerTest.class,IntegrationTest.class,
        CalendarDateTest.class, DateUtilTest.class, DayManagerTest.class})
public class TestAll {

}
