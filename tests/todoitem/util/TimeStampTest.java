package todoitem.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TimeStampTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Class TimeStamp tests begin! Good Luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class TimeStamp tests end! Are you satisfied?");
    }

    @Test
    public void isValid() {
        //assert true cases
        List<TimeStamp> trueCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2016, 2,29, 0 , 0));
                add(new TimeStamp(2016, 1,31, 0 , 0));
                add(new TimeStamp(2016, 4,30, 0 , 0));
                add(new TimeStamp(2019, 1,25, 2 , 59));
                add(new TimeStamp(2020, 12,29, 12 , 0));
                add(new TimeStamp(2027, 11,29, 23 , 59));
            }
        };
        for (int i = 0 ; i < trueCases.size() ; i++){
            assertTrue(trueCases.get(i).isValid());
        }
        //assert false cases
        List<TimeStamp> falseCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2016, 2,31, 0 , 0));
                add(new TimeStamp(2016, 2,-1, 0 , 0));
                add(new TimeStamp(2016, 4,31, 0 , 0));
                add(new TimeStamp(2019, 1,25, 2 , 60));
                add(new TimeStamp(2020, 12,29, 12 , -1));
                add(new TimeStamp(2027, 11,29, -1 , 59));
                add(new TimeStamp(1599, 11,29, 0 , 59));
            }
        };

        for (int i = 0 ; i < falseCases.size(); i++){
            assertFalse(falseCases.get(i).isValid());
        }
    }

    @Test
    public void changeTo() {
        List<TimeStamp> cases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2016, 2,29, 0 , 0));
                add(new TimeStamp(2016, 1,31, 0 , 0));
                add(new TimeStamp(2016, 4,30, 0 , 0));
            }
        };
        List<TimeStamp> testCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2000, 1,21, 6 , 24));
                add(new TimeStamp(2016, 1,31, 0 , 0));
                add(new TimeStamp(2016, 4,30, 0 , 0));
            }
        };
        boolean passFlag = true;
        for (int i = 0 ; i < testCases.size(); i++) {
            TimeStamp testStamp = testCases.get(i);
            cases.get(i).changeTo(testStamp.getYear(),testStamp.getMonth(),testStamp.getDay()
                                    , testStamp.getHour(),testStamp.getMinute());
            if (!(cases.get(i).equals(testStamp))) {
                passFlag = false;
            }
        }
        assertTrue(passFlag);
    }

    @Test
    public void isAfter() {
        TimeStamp testCase = new TimeStamp(2018, 3 , 31 , 5, 24);
        List<TimeStamp> afterCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2018, 3,31, 5 , 25));
                add(new TimeStamp(2018, 4,29, 12 , 1));
                add(new TimeStamp(2019, 3,29, 23 , 59));
            }
        };
        List<TimeStamp> beforeCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2018, 3,31, 5 , 24));
                add(new TimeStamp(2016, 1,31, 0 , 0));
                add(new TimeStamp(2016, 3,31, 1 , 0));
            }
        };
        for (int i = 0 ; i < beforeCases.size();i++){
            assertTrue(testCase.isAfter(beforeCases.get(i)));
        }
        for (int i = 0 ; i < beforeCases.size();i++){
            assertFalse(testCase.isAfter(afterCases.get(i)));
        }
    }

    @Test
    public void isBefore() {
        TimeStamp testCase = new TimeStamp(2018, 3 , 31 , 5, 24);
        List<TimeStamp> afterCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2018, 3,31, 5 , 24));
                add(new TimeStamp(2018, 4,29, 12 , 1));
                add(new TimeStamp(2019, 3,29, 23 , 59));
            }
        };
        List<TimeStamp> beforeCases = new ArrayList<TimeStamp>(){
            {
                // date is true
                add(new TimeStamp(2018, 3,31, 5 , 23));
                add(new TimeStamp(2016, 1,31, 0 , 0));
                add(new TimeStamp(2016, 3,31, 1 , 0));
            }
        };
        for (int i = 0 ; i < beforeCases.size();i++){
            assertFalse(testCase.isBefore(beforeCases.get(i)));
        }
        for (int i = 0 ; i < beforeCases.size();i++){
            assertTrue(testCase.isBefore(afterCases.get(i)));
        }
    }

    @Test
    public void delta() {
        TimeStamp testCase = new TimeStamp(2018, 3 , 31 , 5, 24);
        for(int i = 1; i < 10 ; i++) {
            TimeStamp timeStamp = new TimeStamp(testCase.getYear() , testCase.getMonth(),testCase.getDay()-1
            ,testCase.getHour()+i , testCase.getMinute()+i);
            assertTrue((timeStamp.delta(testCase)) == (i*61 - 1*24*60));
        }
    }
}