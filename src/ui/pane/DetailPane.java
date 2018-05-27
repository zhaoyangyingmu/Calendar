package ui.pane;

import io.ItemIO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import kernel.Display;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.AppointmentItem;
import todoitem.itemSub.MeetingItem;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;

public class DetailPane extends GridPane {
    public DetailPane(TimeStamp from , TimeStamp to) {
        int mainCol = 0 ;
        int mainRow = 0 ;
        this.getStyleClass().add("mainContent");
        this.getStylesheets().add("/stylesheet/buttonAndLabel.css");
        Label title = new Label("Detail");
        title.getStyleClass().add("title");
        this.add(title , mainCol , mainRow++);

        ArrayList<Item> itemList = ItemManager.getInstance().getItemsByStamp(from, to);
        GridPane detailContent = new GridPane();
        for (int i = 0; i < itemList.size(); i++) {
            ItemPane itemPane = new ItemPane(itemList.get(i));
            detailContent.add(itemPane, 0, i);
        }
        ScrollPane scrollContent = new ScrollPane();
        scrollContent.setContent(detailContent);
        scrollContent.getStyleClass().add("scrollView");
        this.add(scrollContent, mainCol, mainRow++);

        HBox btRow = new HBox();
        Button addBt = new Button("add");
        addBt.setOnMouseClicked(event -> {
            Item item = null;
            try {
                item = new MeetingItem(from, to , "","","" );
                ItemManager.getInstance().addItem(item);
                ItemIO.output();
                Display.removeDetailPane();
                Display.addEditPane(item, true);
            } catch (Exception e) {
                Display.showToast("请输入正确的时间与正确的类型！");
            }
        });
        addBt.getStyleClass().add("btn");
        addBt.setMaxSize(45 , 30);
        addBt.setMinSize(45 , 30);
        Button quitBt = new Button("quit");
        quitBt.setOnMouseClicked(event -> {
            Display.removeDetailPane();
        });
        quitBt.getStyleClass().add("btn");
        quitBt.setMaxSize(45, 30);
        addBt.setMinSize(45 , 30);
        btRow.getChildren().add(0, addBt);
        btRow.getChildren().add(1,quitBt);
        btRow.setMargin(addBt , new Insets(10, 10 , 10 , 0));
        btRow.setMargin(quitBt , new Insets(10,0,10,10));
        btRow.getStyleClass().add("buttons");

        this.add(btRow, mainCol, mainRow++);
        btRow.setAlignment(Pos.CENTER);

        this.setStyle("-fx-background-color: rgba(255 , 255 , 255 , 0.9);-fx-background-radius: 5.0;");
    }

    private class ItemPane extends GridPane {
        private Item item;
        public ItemPane(Item item) {
            // TODO: 2018/5/11
            this.item = item;
            int rowIndex = 0;
            Line line = new Line(0 , 0 , 500 , 0);
            line.setStroke(Color.YELLOW);
            this.add(line , 0, rowIndex++);

            Text fromToText = new Text("From: " + item.getFrom().toString() + " To: " + item.getTo().toString());
            this.add(fromToText,0 , rowIndex++);
            this.setMargin(fromToText, new Insets(5, 0 , 0 , 0 ));

            String typeStr = "Type: " + item.getItemType().getTypeStr();
            Text typeText = new Text(typeStr);
            this.add(typeText,0 , rowIndex++);
            this.setMargin(typeText, new Insets(5, 0 , 0 , 0 ));


            Text detailText = new Text("Detail: " + item.getDetailText());
            this.add(detailText, 0 , rowIndex++);
            this.setMargin(detailText, new Insets(5, 0 , 0 , 0 ));


            rowIndex = addOtherInfo(this,item,rowIndex);

            GridPane buttonPane = new GridPane();
            Button removeBt = new Button("remove");
            removeBt.getStyleClass().add("redInDetail");
            removeBt.setMaxSize(80, 23);
            removeBt.setMinSize(80, 23);
            removeBt.setOnMouseClicked(event -> {
                ItemManager.getInstance().deleteItem(item);
                ItemIO.output();
                Display.refreshDetailPane();
                BodyPane.getInstance().refresh();
            });
            removeBt.setCursor(Cursor.HAND);

            Button editBt = new Button("edit");
            editBt.getStyleClass().add("greenInDetail");
            editBt.setOnMouseClicked(event -> {
                Display.removeDetailPane();
                Display.addEditPane(item, false);
            });
            editBt.setMaxSize(80, 23);
            editBt.setMinSize(80, 23);
            editBt.setCursor(Cursor.HAND);

            buttonPane.add(removeBt,0,0);
            buttonPane.add(editBt, 1,0);
            buttonPane.setHgap(10);
            this.add(buttonPane,0,rowIndex++);
            this.setMargin(buttonPane, new Insets(5, 0 , 0 , 0 ));
            this.setPadding(new Insets(10, 10,0 , 10));
            this.getStylesheets().add("/stylesheet/greenAndRed.css");
        }

        private int addOtherInfo(ItemPane itemPane,Item item,int rowIndex){
            if (item.getItemType() == Item.ItemType.APPOINTMENT){//约会则还需显示人员和地点
                Text participantText = new Text("Participants: " + ((AppointmentItem)item).getParticipants());
                itemPane.add(participantText, 0 , rowIndex++);
                itemPane.setMargin(participantText, new Insets(5, 0 , 0 , 0 ));

                Text locationText = new Text("Location: " + ((AppointmentItem)item).getLocation());
                itemPane.add(locationText, 0 , rowIndex++);
                itemPane.setMargin(locationText, new Insets(5, 0 , 0 , 0 ));
                return rowIndex;
            }else if(item.getItemType() == Item.ItemType.MEETING){//会议还需显示地点和主题
                Text locationText = new Text("Location: " + ((MeetingItem)item).getLocation());
                itemPane.add(locationText, 0 , rowIndex++);
                itemPane.setMargin(locationText, new Insets(5, 0 , 0 , 0 ));

                Text topicText = new Text("Topic: " + ((MeetingItem)item).getTopic());
                itemPane.add(topicText, 0 , rowIndex++);
                itemPane.setMargin(topicText, new Insets(5, 0 , 0 , 0 ));
                return rowIndex;
            }else{
                //其他则什么都不干,直接返回rowIndex

                return rowIndex;
            }

        }
    }
}
