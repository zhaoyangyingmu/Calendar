import kernel.CalendarDateTest;
import kernel.DateUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import todoitem.MemoManagerTest;
import todoitem.MemoTest;
import todoitem.util.TimeStampTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({TimeStampTest.class, MemoTest.class , MemoManagerTest.class, CalendarDateTest.class, DateUtilTest.class})
public class TestAll {

}
