package ui.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class DetailPane extends GridPane {
    public DetailPane(TimeStamp from , TimeStamp to) {
        this.setStyle("-fx-background-color: white;");
        this.setMaxHeight(325);
        this.setMinHeight(325);
        this.setMaxWidth(525);
        this.setMinWidth(525);
        Label title = new Label("Detail");
        title.setStyle("-fx-background-color: white;");
        this.add(title, 0 , 0);
        title.setAlignment(Pos.CENTER);
        ArrayList<Item> itemList = ItemManager.getInstance().getItemsByStamp(from, to);
        //        for (int i = 0 ; i < itemList.size() ; i++) {
//            ItemPane itemPane = new ItemPane(itemList.get(i));
//            this.add(itemPane, 0, i+1);
//        }
        /**
         * disable item pane
         * */
        GridPane detailContent = new GridPane();
        for ( int i = 0 ; i < itemList.size(); i++) {
            ItemPane itemPane = new ItemPane(itemList.get(i));
            detailContent.add(itemPane, 0, i);
        }
        this.add(detailContent , 0 , 1);
        Button addBt = new Button("add");
        addBt.setOnMouseClicked(event -> {
            Item item = new Item(from, to , "" , Item.ItemType.LEISURE);
            ItemManager.getInstance().addItem(item);
            Display.removeDetailPane();
            Display.addEditPane(item);
        });
        Button quitBt = new Button("quit");
        quitBt.setOnMouseClicked(event -> {
            Display.removeDetailPane();
        });
        this.add(addBt , 0 , 2);
        this.add(quitBt , 0 , 3);
    }

    private class ItemPane extends GridPane {
        private Item item;
        public ItemPane(Item item) {
            this.item = item;
            String typeStr = "Type: " + item.getItemType().getTypeStr();
            Text typeText = new Text(typeStr);
            this.add(typeText,0 , 0);
            Text fromToText = new Text("From: " + item.getFrom().toString() + " To: " + item.getTo().toString());
            this.add(fromToText,0 , 1);
            Text detailText = new Text("Detail: " + item.getDetailText());
            this.add(detailText, 0 , 2);
            GridPane buttonPane = new GridPane();
            Button removeBt = new Button("remove");
            removeBt.setOnMouseClicked(event -> {
                ItemManager.getInstance().deleteItem(item);
                Display.refreshDetailPane();
                BodyPane.getInstance().refresh();
            });
            Button editBt = new Button("edit");
            editBt.setOnMouseClicked(event -> {
                Display.removeDetailPane();
                Display.addEditPane(item);
            });
            buttonPane.add(removeBt,0,0);
            buttonPane.add(editBt, 1,0);
            this.add(buttonPane,0,3);
        }
    }
}
