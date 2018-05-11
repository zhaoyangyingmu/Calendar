import io.ItemIO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bing Chen on 2018/5/12.
 */
public class IntegrationTestIO {
    private final static ArrayList<Item> expectedItems = new ArrayList<>();


//    @Before
//    public void delete() {
//        File file = new File("output.txt");
//        if (file.exists())
//            file.delete();
//    }

    @Test
    public void IOTest() {
        File file = new File("output.txt");
        if (file.exists()) {
            file.delete();
        }
        ItemIO.input();
        ItemManager manager = ItemManager.getInstance();
        ArrayList<Item> items = manager.getItemList();
        assertEquals(expectedItems.size(), items.size());

        for (int i = 0; i < expectedItems.size(); i++) {
            assertEquals(expectedItems.get(i), items.get(i));
            assertEquals(expectedItems.get(i).getFrom(), items.get(i).getFrom());
            assertEquals(expectedItems.get(i).getTo(), items.get(i).getTo());
            assertEquals(expectedItems.get(i).getItemType(), items.get(i).getItemType());
            assertEquals(expectedItems.get(i).getDetailText(), items.get(i).getDetailText());
        }
        for (int i = 0; i < 5; i++) {
            Item item = null;
            try {
                item = new OtherItem(new TimeStamp(2018, 5, i + 1, 0, 0),
                        new TimeStamp(2018, 5, i + 1, 23, 59),
                        "");
            } catch (Exception e) {
                e.printStackTrace();
            }
            expectedItems.add(item);
            manager.addItem(item);
            ItemIO.output();
            ItemManager.destroy();
            ItemIO.input();
            assertEquals(expectedItems.size(), manager.getItemList().size());
        }
        int size = expectedItems.size();
        System.out.println(size);
        for (Item item : expectedItems) {
            manager.deleteItem(item);
            size--;
            ItemIO.output();
            ItemManager.destroy();
            ItemIO.input();
            assertEquals(size, manager.getItemList().size());
        }
        expectedItems.clear();

    }
}
