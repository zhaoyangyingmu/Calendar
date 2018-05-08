package todoitem;

import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class ItemManager {
    private static volatile ItemManager itemManager;    //volatile关键字保证不能同时对itemManager的读写
    private ArrayList<Item> itemList = new ArrayList<>();

    private ItemManager() {

    }

    public static ItemManager getInstance() {
        if (itemManager == null) {  //降低同步锁发生概率
            synchronized (ItemManager.class) {  //添加同步锁，保证线程安全
                if (itemManager == null) {
                    itemManager = new ItemManager();
                }
            }
        }
        return itemManager;
    }

    public static void destroy() {
        itemManager = null;
    }

    public ArrayList<Item> getItemsByStamp(TimeStamp from, TimeStamp to) {
        ArrayList<Item> resultList = new ArrayList<>();
        for (Item tmp : itemList) {
            if (tmp.isDuringTime(from, to)) {
                resultList.add(tmp);
            }
        }
        return resultList;
    }

    /**
     * what if add the same thing twice;
     * how about sorting them in an order;
     */
    public void addItem(Item item) {
        itemList.add(item);
    }

    /**
     * what if delete the same thing twice;
     */
    public void deleteItem(Item item) {
        itemList.remove(item);
    }

}
