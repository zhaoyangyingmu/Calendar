package todoitem;

import exception.DataErrorException;
import javafx.collections.FXCollections;
import todoitem.Item.ItemType;
import todoitem.itemSub.AnniversaryItem;
import todoitem.util.TimeStamp;
import todoitem.util.TimeStampFactory;

import java.util.*;

public class ItemManager {
    private final static Set<String> overlappedTypes = new HashSet<>(); //不允许重叠的待办事项类型
    private static volatile ItemManager itemManager;    //volatile关键字保证不能同时对itemManager的读写
    private static ArrayList<Item> itemList = new ArrayList<>();

    private ItemManager() {
        overlappedTypes.addAll(FXCollections.observableArrayList(
                ItemType.MEETING.getTypeStr(), ItemType.DATE.getTypeStr(),
                ItemType.COURSE.getTypeStr(), ItemType.INTERVIEW.getTypeStr(),
                ItemType.TRAVEL.getTypeStr()
        ));
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
        itemListSort();
        return itemList;
    }

    private void itemListSort() {
        if (itemList == null)
            return;
        int length = itemList.size();
        Item tempItem;
        int j;
        for (int i = 1; i < length; i++) {
            tempItem = itemList.get(i);
            for (j = i; j > 0 && tempItem.getPriority() < itemList.get(j - 1).getPriority(); j--) {
                itemList.set(j, itemList.get(j - 1));
            }
            itemList.set(j, tempItem);
        }
    }

    public static void destroy() {
        itemList.clear();
        // 用 itemManager = null 出问题，因为实际上外部可以保存这个单例，导致不是单例。
    }

    /**
     * 更新待办事项的完成状态
     *
     * @param item 待更新的待办事项对象
     */
    public boolean setCompleted(Item item) {
        if (item.getStatus() == Const.IN_PROGRESS) {//在进行中的待办事项才可以设置为完成状态
            if (Mysql.updateStatus(item.getID(), Const.COMPLETED) == Const.COMPLETED) {//更新成功
                ArrayList<HashMap<String, String>> childrenMsg = Mysql.queryByFatherID(item.getFatherID());
                boolean allCompleted = true;
                for (HashMap<String, String> msg : childrenMsg) {
                    allCompleted &= (Integer.parseInt(msg.get("status")) == Const.COMPLETED);
                }
                if (allCompleted)//所有子待办事项都完成后，更新父待办事项
                    return Mysql.updateStatus(item.getFatherID(), Const.COMPLETED) == Const.COMPLETED;
            }
        }
        return false;
    }

    /**
     * @param item 待更新状态的待办事项
     * @return 返回更新后的状态值
     */
    public int updateStatus(Item item) {
        Calendar time = Calendar.getInstance();
        TimeStamp stamp = new TimeStamp(time.get(Calendar.YEAR), time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH),
                time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE));
        TimeStamp from = TimeStampFactory.createStampByString(item.getValue("startTime"));
        TimeStamp to = TimeStampFactory.createStampByString(item.getValue("endTime"));
        int st = item.getStatus(); //待办事项当前完成状态
        int status = Const.BEFORE_BEGINNING;
        if (stamp.isAfter(from) && stamp.isBefore(to) && st == Const.BEFORE_BEGINNING) {
            status = Const.IN_PROGRESS;
        } else if (stamp.isAfter(to) && st == Const.IN_PROGRESS) {
            status = Const.OVERDUE;
        }
        if (st != Const.COMPLETED) {
            if (item.getID() < 0)//还未插入数据库
                item.setStatus(status);
            else
                Mysql.updateStatus(item.getID(), status);//不是完成状态就自动更新，是否完成由用户更新}
            return status;
        }
        return Const.COMPLETED;

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

        long currentMinute = System.currentTimeMillis() / (60 * 1000);
        for (Item tmp : itemList) {
            if (tmp.getFrom() == null || tmp.getTo() == null) {
                continue;
            }
            if (tmp.promptStatus()) {
                long startMinute = tmp.getFrom().getMinutes() - tmp.minutesAhead();
                long endMinute = tmp.getFrom().getMinutes();
                long minutesDelta = tmp.minutesDelta();
                for (long i = startMinute; i <= endMinute; i += minutesDelta) {
                    if (i == currentMinute) {
                        resultList.add(tmp);
                    }
                }
            }
        }
        return resultList;
    }

//    public ArrayList<Item> getItemsByStamp(TimeStamp from, TimeStamp to) {
//        ArrayList<HashMap<String, String>> itemsMsg = Mysql.queryByTime(from.toString(), to.toString());
//        if (itemsMsg != null) {
//            int year = 0;
//            for (HashMap<String, String> msg : itemsMsg) {
//                if (msg.get("type").equals(ItemType.ANNIVERSARY.getTypeStr())) {
//                    if (year == 0) {
//                        TimeStamp stamp = TimeStampFactory.createStampByString(msg.get("startDay"));
//                        if (stamp != null) {    //获取第一次在时间段内的纪念日时间
//                            while (stamp.isBefore(from)) {
//                                TimeStamp temp = stamp;
//                                stamp = TimeStampFactory.createStampDayStart(stamp.getYear() + 1, stamp.getMonth(), stamp.getDay());
//                                if (!stamp.isValid()) {
//                                    stamp = TimeStampFactory.createStampDayStart(temp.getYear() + 2, temp.getMonth(), temp.getDay());
//                                }
//                            }
//                            year = stamp.getYear();
//                        }
//                    }
//                    msg.put("year", year + "");
//                    year++;
//                }
//            }
//        }
//        return getItems(itemsMsg);
//    }

    public ArrayList<Item> getItemsByFatherItem(Item item) {
        ArrayList<HashMap<String, String>> itemsMsg = Mysql.queryByFatherID(item.getID());
        if (item.getItemType().equals(ItemType.ANNIVERSARY)) {//纪念日
            AnniversaryItem anniversaryItem = (AnniversaryItem) item;
            if (itemsMsg != null) {
                for (HashMap<String, String> msg : itemsMsg) {
                    TimeStamp frTime = TimeStampFactory.createStampByString(msg.get("startTime"));//TODO mysql
                    if (frTime != null && frTime.getYear() != anniversaryItem.getYear()) {  //只保留当年的纪念日的子待办事项
                        itemsMsg.remove(msg);
                    }
                }
            }
        }
        return getItems(itemsMsg);
    }

    private ArrayList<Item> getItems(ArrayList<HashMap<String, String>> itemsMsg) {
        ArrayList<Item> resItems = new ArrayList<>();
        for (HashMap<String, String> msg : itemsMsg) {
            ItemType type = ItemType.parseItemType(msg.get("type"));
            Item temp = ItemFactory.createItemByItemType(type, msg);
            updateStatus(temp);
            resItems.add(temp);
        }
        return resItems;
    }

    /**
     * what if add the same thing twice;
     * how about sorting them in an order;
     */
    public void addItem(Item item) {
        if (item != null) {
            itemList.add(item);
            System.out.println("是否是父待办事项: " + item.isFather());
            System.out.println("优先级: " + item.getPriority());
            System.out.println("完成状态: " + item.getStatus());
            System.out.println("是否设置提醒: " + item.promptStatus());
            System.out.println("提前多久提醒: " + item.minutesAhead());
            System.out.println("是否在界面上显示: " + item.showOnStage());
            System.out.println("=====================  分割线  =======================");
        }
    }

    /**
     * @param item    待添加的待办事项
     * @param confirm 确认是否添加
     * @return 添加成功返回true，否则返回false
     */
    public boolean addItem(Item item, boolean confirm) throws DataErrorException {
        if (confirm && item != null)
            if (canOverlapped(item)) {
                updateStatus(item);
                return Mysql.addSchedule(item.getAttrs()) != 0;
            }
        return false;
    }

    /**
     * @param father 待添加的代办事项的父待办事项
     * @param item   待添加的待办事项
     * @return 添加成功返回true
     * @throws DataErrorException 添加失败抛出异常（失败原因）
     */
    public boolean addChildItem(Item father, Item item) throws DataErrorException {
        if (father == null || item == null) {
            throw new DataErrorException("添加失败");
        }
        //保证待办事项的子待办事项不会再能添加子待办事项
        if (!father.isFather()) {
            throw new DataErrorException("子待办事项不能再添加子待办事项了！");
        }

        /*
        *某些待办事项不能互相包含，即他们之间是互斥的
        *如：会议不能有约会待办事项作为子待办事项。
        *目前互斥集合为（会议，课程，约会，面试）
        *但是相同类型不互斥。即会议允许存在子会议。
        **/
        Set<String> types = new HashSet<>();
        types.addAll(FXCollections.observableArrayList(
                ItemType.MEETING.getTypeStr(), ItemType.DATE.getTypeStr(),
                ItemType.COURSE.getTypeStr(), ItemType.INTERVIEW.getTypeStr()));
        if (types.remove(father.getItemType().getTypeStr()))  //true表示父类型为四种类型之一
            if (types.contains(item.getItemType().getTypeStr())) //true表示子待办事项类型为互斥类型
                throw new DataErrorException("会议，课程，约会，面试等为互斥类型！");


        /*
        *父待办事项必须有起止时间，添加的子待办事项时间必须在父待办事项的时间范围内
        *例如: 设置了时间的待办事项不能添加没有设置时间的子待办事项
        *     没有设置时间的待办事项不能设置子待办事项。
        **/
        TimeStamp fatherFr = father.getFrom();
        TimeStamp fatherTo = father.getTo();
        TimeStamp fr = item.getFrom();
        TimeStamp to = item.getTo();
        //必须有起止时间
        if (fatherFr == null || fatherTo == null) {
            throw new DataErrorException("未设置时间的代办事项不能添加子待办事项");
        }
        if (fr == null || to == null)
            throw new DataErrorException("未设置时间的待办事项不能添加为子待办事项");
        if (fr.isBefore(fatherFr) || to.isAfter(fatherTo)) {
            throw new DataErrorException("子待办事项的时间应在父待办事项时间范围内");
        }
        return addItem(item, true);
    }

    /**
     * what if delete the same thing twice;
     */

    public void deleteItem(Item item) {
        itemList.remove(item);
    }

//    public boolean deleteItem(Item item) {
//        if (item.isFather()) {//删除子待办事项
//            boolean deleteAll = true;
//            ArrayList<HashMap<String, String>> childrenMsg = Mysql.queryByFatherID(item.getID());
//            if (childrenMsg != null)
//                for (HashMap<String, String> msg : childrenMsg)
//                    deleteAll &= Mysql.deleteSchedule(Integer.parseInt(msg.get("scheduleID"))) != 0;
//            return deleteAll;
//        } else
//            return Mysql.deleteSchedule(item.getID()) != 0;
//    }
//

    /**
     * 待办事项时间重叠
     *
     * @param item 待添加的待办事项
     */
    private boolean canOverlapped(Item item) throws DataErrorException {
        /*
        *以下类型的待办事项（会议，课程，约会，面试，旅程）不能出现时间重叠情况，包括相同类型的情况
        *后添加的时间冲突的待办事项不能添加成功并且为用户提示错误（具体到与哪个待办事项在XX时间冲突了）
        *父待办事项在计算时间重叠方面和普通地待办事项没有区别)
        *其余类型待办事项可以时间重叠
        *例子：会议不能和会议时间重叠，会议不能和约会时间重叠。会议不能和纪念日的子待办事项-约会时间重叠。
        **/
        ArrayList<HashMap<String, String>> itemsMsg = Mysql.queryByTime(item.getFrom().toString(), item.getTo().toString());
        if (itemsMsg != null)
            for (HashMap<String, String> itemMsg : itemsMsg) {
                if (overlappedTypes.contains(itemMsg.get("type")))
                    throw new DataErrorException("与其他待办事项时间重叠！\n会议，课程，约会，面试，旅程等类型不允许时间重叠！");
            }
        return true;
    }
}
