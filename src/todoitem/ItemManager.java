package todoitem;

import todoitem.itemSub.AppointmentItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class ItemManager {
    private static volatile ItemManager itemManager;    //volatile关键字保证不能同时对itemManager的读写
    private static ArrayList<Item> itemList = new ArrayList<>();

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

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public static void destroy() {
        itemList.clear();
        // 用 itemManager = null 出问题，因为实际上外部可以保存这个单例，导致不是单例。
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

    public ArrayList<Item> getPrompts() {

        ArrayList<Item> resultList = new ArrayList<>();

//        long currentMinute = System.currentTimeMillis() / (60 * 1000) ;
//        for(Item tmp : itemList) {
//            if(tmp.getFrom() == null || tmp.getTo() == null) {
//                continue;
//            }
//            if ((boolean) tmp.getValue("promptStatus")) {
//                long startMinute = tmp.getFrom().getMinutes();
//                long endMinute = tmp.getTo().getMinutes();
//                long minutesDelta = (long) tmp.getValue("minutesDelta");
//                for(long i = startMinute; i <= endMinute ; i+= minutesDelta) {
//                    if (i == currentMinute) {
//                        resultList.add(tmp);
//                        break;
//                    }
//                }
//            }
//        }
        return resultList;
    }

    /**
     * what if add the same thing twice;
     * how about sorting them in an order;
     */
    public void addItem(Item item) {
        if (item != null)
            itemList.add(item);
    }

    /**
     * what if delete the same thing twice;
     */
    public void deleteItem(Item item) {
        itemList.remove(item);
    }

}
