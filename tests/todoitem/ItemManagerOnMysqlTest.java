package todoitem;

import database.Mysql;
import org.junit.Test;
import todoitem.itemSub.AppointmentItem;
import todoitem.itemSub.MeetingItem;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemManagerOnMysqlTest {
    @Test
    public void getInstance() throws Exception {
        ItemManager manager1 = ItemManager.getInstance();
        ItemManager manager2 = ItemManager.getInstance();
        assertEquals(manager1, manager2);
        ItemManager.destroy();
        ItemManager manager3 = ItemManager.getInstance();   //单例模式
        assertEquals(manager2, manager3);
    }

    @Test
    public void setCompleted() {
        try {
            Mysql mysql = Mysql.getInstance();
            mysql.clear();
            // 只有处于进行中的待办事项才能设置成完成状态

            // 对于含有子待办事项的待办事项不能直接设置成完成状态

            // 当所有子待办事项都完成时，将父待办事项设置为完成状态
            ItemManager manager = ItemManager.getInstance();

            ArrayList<Item> items = new ArrayList<Item>() {
                {
                    add(new OtherItem(new TimeStamp(2018, 5, 26, 0, 0),
                            new TimeStamp(2019, 6, 1, 23, 50),
                            "进行中"));

                    add(new OtherItem(new TimeStamp(2018, 5, 20, 0, 0),
                            new TimeStamp(2018, 5, 24, 23, 50),
                            "已过期"));

                    add(new OtherItem(new TimeStamp(2019, 6, 2, 0, 0),
                            new TimeStamp(2019, 6, 2, 23, 59),
                            "未开始"));

                    add(new OtherItem(new TimeStamp(2018, 2, 20, 0, 1),
                            new TimeStamp(2018, 2, 26, 0, 2),
                            "已过期"));

                    add(new OtherItem(new TimeStamp(2018, 4, 21, 23, 1),
                            new TimeStamp(2019, 6, 30, 16, 6), "进行中"));

                    add(new OtherItem(new TimeStamp(2018, 5, 20, 5, 42),
                            new TimeStamp(2020, 7, 30, 5, 43), "进行中"));

                    add(new OtherItem(new TimeStamp(2018, 5, 20, 5, 42),
                            new TimeStamp(2021, 7, 30, 5, 43), "进行中，含子待办事项"));

                    add(new OtherItem(new TimeStamp(2018, 5, 20, 5, 42),
                            new TimeStamp(2022, 7, 30, 5, 43), "进行中，含子待办事项"));
                }
            };
            for (Item item : items) {
                manager.addItem(item, true);

            }
//        ArrayList<Item> items = new ArrayList<>();
            items = manager.getItemsByStamp(
                    new TimeStamp(2018, 1, 1, 0, 0),
                    new TimeStamp(2022, 12, 31, 23, 59)
            );
            for (Item item : items) {
                if (item.getStatus() == Const.OVERDUE || item.getStatus() == Const.BEFORE_BEGINNING
                        || item.getStatus() == Const.COMPLETED) { //未开始或已过期或已完成
                    assertFalse(manager.setCompleted(item));
                } else if (item.getDetailText().equals("进行中，含子待办事项")) {//进行中，含子待办事项
                    //添加子待办事项
                    Item item1 = new OtherItem(new TimeStamp(2018, 5, 20, 6, 0),
                            new TimeStamp(2019, 5, 20, 0, 0),
                            "已完成", Const.PRIORITY, Const.COMPLETED, false);
                    Item item2 = new OtherItem(new TimeStamp(2018, 5, 20, 8, 0),
                            new TimeStamp(2020, 2, 10, 0, 0),
                            "已完成", Const.PRIORITY, Const.COMPLETED, false);
                    item1.addAttr("fatherID", item.getID() + "");
                    item2.addAttr("fatherID", item.getID() + "");
                    manager.addChildItem(item1);
                    manager.addChildItem(item2);
                    ArrayList<Item> children = manager.getItemsByFatherItem(item);
                    for (Item child : children) {
                        manager.setCompleted(child);
                    }
//                    ArrayList<Item> father = manager.getItemsByStamp(item.getFrom(), item.getTo());
//                    for (Item it : father) {
//                        assertEquals(Const.COMPLETED, it.getStatus());
//                    }
                    //TODO 获取新的父待办事项
                    assertEquals(Const.COMPLETED, item.getStatus());
                } else if (item.getDetailText().equals("进行中")) {
                    assertTrue(manager.setCompleted(item));
                }
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Test
    public void updateStatus() throws Exception {
        // 测试能否正确更新状态
        // 1、未开始

        // 2、进行中

        // 3、过期
    }

    @Test
    public void getItemsByStamp() throws Exception {
        // 测试能否正确查询到纪念日、课程信息
    }

    @Test
    public void addItem() throws Exception {
        //一、测试互斥类型不允许作父子的情况


        //二、测试时间段不允许重叠的情况
        /**
         *  1、纪念日
         */

        /**
         * 2、课程
         */

    }

    @Test
    public void deleteItem() throws Exception {

    }

}