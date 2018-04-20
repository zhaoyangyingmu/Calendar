package ui.pane;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class DetailPane extends GridPane {
    public DetailPane(TimeStamp from , TimeStamp to) {
        Text title = new Text("Detail");
        this.add(title, 0 , 0);
        ArrayList<Item> itemList = ItemManager.getInstance().getItemsByStamp(from, to);
        for (int i = 0 ; i < itemList.size() ; i++) {
            ItemPane itemPane = new ItemPane(itemList.get(i));
            this.add(itemPane, 0, i+1);
        }
    }

}
