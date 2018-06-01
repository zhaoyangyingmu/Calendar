package io;

import exception.DataErrorException;
import org.junit.Test;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.AppointmentItem;
import todoitem.util.TimeStamp;
import todoitem.util.TimeStampFactory;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ItemIOTest {
    @Test
    public void outputAndInput() {
        File file = new File("test.txt");
        if (file.exists())
            file.delete();
        final int testSize = 100;
        // expected item list
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
            try {
                Item item = new AppointmentItem(startList.get(i), endList.get(i), "date everyday",
                        "Girlfriend", "Fudan");
                list.add(item);
            } catch (Exception e) {
                System.out.println();
            }

        }

        // ItemManager.add()

        for (Item aList : list) {
            try {
                ItemManager.getInstance().addItem(aList, true);
            } catch (DataErrorException e) {
                System.out.println();
            }
        }
        // output
        ItemIO.output("test.txt");
        // input
        ItemManager.destroy();
        ItemIO.input("test.txt");
        //compare
        for (int i = 0; i < list.size(); i++) {
            assertTrue(ItemManager.getInstance().getItemList().get(i).equals(list.get(i)));
        }
    }
}