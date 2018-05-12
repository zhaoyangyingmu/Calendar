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
    public void setUp(){
        System.out.println("Class ItemManager tests begin! Good Luck!");
    }

    @After
    public void tearDown(){
        System.out.println("Class ItemManager tests end! Are you satisfied?");
        ItemManager.destroy();
    }

    @Test
    public void addItem() throws Exception {
        boolean noSuchItem = false;
        List<Item> items = new ArrayList<Item>() {
            {
                add(new OtherItem(new TimeStamp(2016, 2,29, 0 , 1),
                        new TimeStamp(2016, 2,29, 0 , 2), ""));

                add(new OtherItem(new TimeStamp(2013, 1,31, 23 , 1),
                        new TimeStamp(2014, 1,31, 16 , 6), ""));

                add(new OtherItem(new TimeStamp(2018, 6,30, 5 , 42),
                        new TimeStamp(2018, 6,30, 5 , 43), ""));
            }
        };
        for (int i = 0;i < items.size();i++) {
            ItemManager.getInstance().addItem(items.get(i));
        }
        here:
        for (int i = 0 ; i < items.size();i++) {
            List<Item> resultList = ItemManager.getInstance().getItemsByStamp(items.get(i).getFrom(),items.get(i).getTo());
            if (resultList.size() == 0) {
                noSuchItem = true;
                break;
            }
            for (int j = 0 ; j < resultList.size(); j++) {
                if (resultList.get(j).equals(items.get(i))){
                    continue here;
                }
            }
            noSuchItem = true;
            break;
        }
        assertFalse(noSuchItem);
    }

    @Test
    public void getItemsByStamp()  {
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
        List<Item> items = new ArrayList<Item>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    try {
                        add(new OtherItem(fromCases.get(i),toCases.get(i),""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        for (int i = 0 ; i < items.size();i++) {
            ItemManager.getInstance().addItem(items.get(i));
        }
        boolean pass = true;
        for (int i = 0 ; i < items.size();i++){
            List<Item> actualList = ItemManager.getInstance()
                    .getItemsByStamp(items.get(i).getFrom(),items.get(i).getTo());
            if (!actualList.get(0).equals(items.get(i))){
                pass = false;
                break;
            }
        }
        assertTrue(pass);
    }

    @Test
    public void deleteItem(){
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
        List<Item> items = new ArrayList<Item>() {
            {
                for (int i = 0 ; i < fromCases.size(); i++){
                    try {
                        add(new OtherItem(fromCases.get(i),toCases.get(i),""));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        for (int i = 0 ; i < items.size();i++) {
            ItemManager.getInstance().addItem(items.get(i));
        }
        boolean pass = true;
        for (int i = 0 ; i < items.size();i++){
            List<Item> actualList = ItemManager.getInstance()
                    .getItemsByStamp(items.get(i).getFrom(),items.get(i).getTo());
            ItemManager.getInstance().deleteItem(actualList.get(0));
            List<Item> listAfterDelete = ItemManager.getInstance()
                    .getItemsByStamp(items.get(i).getFrom(),items.get(i).getTo());
            if (listAfterDelete.size() != 0) {
                pass = false;
                break;
            }
        }
        assertTrue(pass);
    }
}