import kernel.CalendarDateTest;
import kernel.DateUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import todoitem.ItemManagerTest;
import todoitem.ItemTest;
import todoitem.util.TimeStampTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({TimeStampTest.class, ItemTest.class , ItemManagerTest.class, CalendarDateTest.class, DateUtilTest.class})
public class TestAll {

}
