package todoitem;

import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class ItemManager {
    private static ItemManager itemManager;
    private ItemManager() {

    }

    public ItemManager getInstance() {
        if (itemManager == null) {
            itemManager = new ItemManager();
        }
        return itemManager;
    }

    public ArrayList<Item> getItemsByStamp(TimeStamp from , TimeStamp to) {
        return new ArrayList<Item>();
    }

    public void addItem(Item item){

    }

    public void deleteItem(Item item) {

    }

}
