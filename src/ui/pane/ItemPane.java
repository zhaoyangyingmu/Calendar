package ui.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import todoitem.Item;

public class ItemPane extends GridPane {
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
        Button editBt = new Button("edit");
        buttonPane.add(removeBt,0,0);
        buttonPane.add(editBt, 1,0);
        this.add(buttonPane,0,3);
    }
}
