package todoitem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemManagerTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("Class ItemManager tests begin! Good Luck!");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Class ItemManager tests end! Are you satisfied?");
        ItemManager.destroy();
    }

    @Test
    public void addItem() {
        boolean noSuchItem = false;
        List<OtherItem> memos = new ArrayList<OtherItem>() {
            {
                add(new OtherItem(new TimeStamp(2016, 2,29, 0 , 1),
                        new TimeStamp(2016, 2,29, 0 , 2), "", OtherItem.ItemType.LEISURE));

                add(new OtherItem(new TimeStamp(2013, 1,31, 23 , 1),
                        new TimeStamp(2014, 1,31, 16 , 6), "", OtherItem.ItemType.LEISURE));

                add(new OtherItem(new TimeStamp(2018, 6,30, 5 , 42),
                        new TimeStamp(2018, 6,30, 5 , 43), "", OtherItem.ItemType.LEISURE));
            }
        };
        for (int i = 0; i < memos.size(); i++) {
            ItemManager.getInstance().addItem(memos.get(i));
        }
        here:
        for (int i = 0; i < memos.size(); i++) {
            List<Item> resultList = ItemManager.getInstance().getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
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
        List<OtherItem> memos = new ArrayList<OtherItem>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    add(new OtherItem(fromCases.get(i),toCases.get(i),"" , OtherItem.ItemType.LEISURE));
                }
            }
        };

        for (int i = 0; i < memos.size(); i++) {
            ItemManager.getInstance().addItem(memos.get(i));
        }
        boolean pass = true;
        for (int i = 0; i < memos.size(); i++){
            List<Item> actualList = ItemManager.getInstance()
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
        List<OtherItem> memos = new ArrayList<OtherItem>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    add(new OtherItem(fromCases.get(i),toCases.get(i),"" , OtherItem.ItemType.LEISURE));
                }
            }
        };
        for (int i = 0; i < memos.size(); i++) {
            ItemManager.getInstance().addItem(memos.get(i));
        }
        boolean pass = true;
        for (int i = 0; i < memos.size(); i++){
            List<Item> actualList = ItemManager.getInstance()
                    .getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            ItemManager.getInstance().deleteItem(actualList.get(0));
            List<Item> listAfterDelete = ItemManager.getInstance()
                    .getItemsByStamp(memos.get(i).getFrom(), memos.get(i).getTo());
            if (listAfterDelete.size() != 0) {
                pass = false;
                break;
            }
        }
        assertTrue(pass);
    }
}