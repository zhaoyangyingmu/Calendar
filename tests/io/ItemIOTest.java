package io;

import org.junit.Test;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;
import todoitem.util.TimeStampFactory;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemIOTest {
    @Test
    public void outputAndInput() {
        final int testSize = 100;
        // expected item list
        TimeStamp start = TimeStamp.createStampDayStart(2018, 1, 1);
        ArrayList<Item> list = new ArrayList<>();

        ArrayList<TimeStamp> startList = new ArrayList<>();
        for (int i = 0; i < testSize; i++) {
            startList.add(TimeStampFactory.createStampDayStart(2018, 1, i + 1));
        }
        ArrayList<TimeStamp> endList = new ArrayList<>();
        for (int i = 0; i < testSize; i++) {
            endList.add(TimeStampFactory.createStampDayEnd(2018, 2, i + 1));
        }

        for (int i = 0; i < testSize; i++) {
            list.add(new OtherItem(startList.get(i), endList.get(i), "abc" + i));
        }

        // ItemManager.add()

        for (int i = 0; i < testSize; i++) {
            ItemManager.getInstance().addItem(list.get(i));
        }
        // output
        ItemIO.output();
        // input
        ItemManager.getInstance().getItemList().clear();
        ItemIO.input();
        //compare
        for (int i = 0; i < testSize; i++) {
            assertTrue(ItemManager.getInstance().getItemList().get(i).equals(list.get(i)));
        }
    }
}