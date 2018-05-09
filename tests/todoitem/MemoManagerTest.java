package todoitem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import todoitem.itemSub.OtherMemo;
import todoitem.util.TimeStamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MemoManagerTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Class MemoManager tests begin! Good Luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class MemoManager tests end! Are you satisfied?");
        MemoManager.destroy();
    }

    @Test
    public void addItem() {
        boolean noSuchItem = false;
        List<OtherMemo> memos = new ArrayList<OtherMemo>() {
            {
                add(new OtherMemo(new TimeStamp(2016, 2,29, 0 , 1),
                        new TimeStamp(2016, 2,29, 0 , 2), "", OtherMemo.ItemType.LEISURE));

                add(new OtherMemo(new TimeStamp(2013, 1,31, 23 , 1),
                        new TimeStamp(2014, 1,31, 16 , 6), "", OtherMemo.ItemType.LEISURE));

                add(new OtherMemo(new TimeStamp(2018, 6,30, 5 , 42),
                        new TimeStamp(2018, 6,30, 5 , 43), "", OtherMemo.ItemType.LEISURE));
            }
        };
        for (int i = 0; i < memos.size(); i++) {
            MemoManager.getInstance().addItem(memos.get(i));
        }
        here:
        for (int i = 0; i < memos.size(); i++) {
            List<Memo> resultList = MemoManager.getInstance().getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            if (resultList.size() == 0) {
                noSuchItem = true;
                break;
            }
            for (int j = 0 ; j < resultList.size(); j++) {
                if (resultList.get(j).equals(memos.get(i))){
                    continue here;
                }
            }
            noSuchItem = true;
            break;
        }
        assertFalse(noSuchItem);
    }

    @Test
    public void getItemsByStamp() {
        List<TimeStamp> fromCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2016, 2,29, 0 , 0));
                add(new TimeStamp(2013, 1,31, 16 , 6));
                add(new TimeStamp(2018, 6,30, 5 , 42));
            }
        };
        List<TimeStamp> toCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2017, 2,29, 6 , 31));
                add(new TimeStamp(2014, 1,31, 16 , 6));
                add(new TimeStamp(2019, 6,30, 5 , 43));
            }
        };
        List<OtherMemo> memos = new ArrayList<OtherMemo>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    add(new OtherMemo(fromCases.get(i),toCases.get(i),"" , OtherMemo.ItemType.LEISURE));
                }
            }
        };

        for (int i = 0; i < memos.size(); i++) {
            MemoManager.getInstance().addItem(memos.get(i));
        }
        boolean pass = true;
        for (int i = 0; i < memos.size(); i++){
            List<Memo> actualList = MemoManager.getInstance()
                    .getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            if (!actualList.get(0).equals(memos.get(i))){
                pass = false;
                break;
            }
        }
        assertTrue(pass);
    }

    @Test
    public void deleteItem() {
        List<TimeStamp> fromCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2016, 2,29, 0 , 0));
                add(new TimeStamp(2013, 1,31, 16 , 6));
                add(new TimeStamp(2018, 6,30, 5 , 42));
            }
        };
        List<TimeStamp> toCases = new ArrayList<TimeStamp>(){
            {
                add(new TimeStamp(2017, 2,29, 6 , 31));
                add(new TimeStamp(2014, 1,31, 16 , 6));
                add(new TimeStamp(2019, 6,30, 5 , 43));
            }
        };
        List<OtherMemo> memos = new ArrayList<OtherMemo>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    add(new OtherMemo(fromCases.get(i),toCases.get(i),"" , OtherMemo.ItemType.LEISURE));
                }
            }
        };
        for (int i = 0; i < memos.size(); i++) {
            MemoManager.getInstance().addItem(memos.get(i));
        }
        boolean pass = true;
        for (int i = 0; i < memos.size(); i++){
            List<Memo> actualList = MemoManager.getInstance()
                    .getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            MemoManager.getInstance().deleteItem(actualList.get(0));
            List<Memo> listAfterDelete = MemoManager.getInstance()
                    .getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            if (listAfterDelete.size() != 0) {
                pass = false;
                break;
            }
        }
        assertTrue(pass);
    }
}