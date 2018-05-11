package io;

import todoitem.Item;
import todoitem.ItemManager;

import java.io.*;
import java.util.ArrayList;

public class ItemIO {
    public static void output(String path) {
        ArrayList<Item> list = ItemManager.getInstance().getItemList();
//        if (list.isEmpty()) {
//            return;
//        }

        File file = new File(path);
        if (file.exists()) {
            file.delete();
            file = new File(path);
        }
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            for(int i = 0 ; i < list.size() ; i++) {
                objectOutputStream.writeObject(list.get(i));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size() == 0) {
            file.delete();
        }

    }

    public static void output() {
        output("output.txt");
    }

    public static void input(String path) {
        File file = new File(path);
        if(!file.exists()) {
            return;
        }

        try(
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objInput = new ObjectInputStream(fileInputStream);
        ) {
            while(true) {
                Item item = (Item) objInput.readObject();
                ItemManager.getInstance().addItem(item);
            }
        }catch (EOFException e) {

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void input() {
        input("output.txt");
    }
}

