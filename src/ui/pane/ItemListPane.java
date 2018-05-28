package ui.pane;

import io.ItemIO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kernel.Display;
import todoitem.Const;
import todoitem.Item;
import todoitem.ItemManager;
import todoitem.itemSub.OtherItem;
import todoitem.util.TimeStamp;

import java.util.ArrayList;


public class ItemListPane extends VBox {
    private ArrayList<Item> memos;
    private Label numLabel;
    private Label addLabel;
    private TimeStamp frTime;
    private TimeStamp toTime;
    private Stage stage;

    public ItemListPane(TimeStamp frTime, TimeStamp toTime, Stage stage) {
        this.memos = ItemManager.getInstance().getItemsByStamp(frTime, toTime);
        this.frTime = frTime;
        this.toTime = toTime;
        this.stage = stage;
        this.setId("memoListPane");
        this.getStylesheets().add(ItemListPane.class.getResource("../../stylesheet/ItemListPane.css").toString());
        numLabel = new Label("Total " + memos.size() + " to-do");
        addLabel = new Label("+");
        paint();
        bindEvent();
    }

    private void paint() {
        stage.initStyle(StageStyle.UTILITY);
        Insets insets = new Insets(10);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-border-color: transparent; -fx-border-style: hidden");
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(numLabel);
        borderPane.setRight(addLabel);
        borderPane.setPadding(insets);
        borderPane.setPrefWidth(400);

        numLabel.setStyle("-fx-border-color: transparent transparent #D3E397 transparent");
        numLabel.setId("num_label");
        addLabel.setAlignment(Pos.TOP_CENTER);
        addLabel.setId("add_label");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(insets);
        for (Item memo : memos) {
            BorderPane pane = new BorderPane();
            pane.setPrefWidth(380);
            pane.getStyleClass().add("item");
            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.setAlignment(Pos.TOP_LEFT);
            hBox.setPadding(insets);
            ItemPane itemPane = new ItemPane(memo);
            itemPane.setOnMouseClicked(event -> {
                //TODO 进入详情界面
            });
            Label label = new Label("");
            Label delLabel = new Label("DEL");

            pane.setLeft(hBox);
            pane.setRight(delLabel);
            hBox.getChildren().addAll(label, itemPane);
            vBox.getChildren().add(pane);

            delLabel.setAlignment(Pos.CENTER_RIGHT);
            delLabel.getStyleClass().addAll("del_label", "label");
            delLabel.setOnMouseClicked(event -> {
                ItemManager.getInstance().deleteItem(memo);
                ItemIO.output();
                vBox.getChildren().remove(pane);
                numLabel.setText("Total " + ItemManager.getInstance().getItemsByStamp(frTime, toTime).size() + " to-do");
                BodyPane.getInstance().refresh();
            });
        }
        scrollPane.setContent(vBox);
        scrollPane.setPrefSize(400, 400);
        this.getChildren().addAll(borderPane, scrollPane);
    }


    private void bindEvent() {
        addLabel.setOnMouseClicked(event -> {
            Item item = null;
            try {
                item = new OtherItem(this.frTime, this.toTime, "");
                ItemManager.getInstance().addItem(item);
                ItemIO.output();
                stage.close();
                Display.addEditPane(item, true);
            } catch (Exception e) {
                Display.showToast("请输入正确的时间与正确的类型！");
            }
        });
    }

    class ItemPane extends VBox {
        private Item memo;
        private Label typeLabel;
        private Label descLabel;
        private Label frLabel;
        private Label toLabel;
        private Label statusLabel;

        ItemPane(Item memo) {
            this.memo = memo;
            init();
        }

        private void init() {
            Insets insets = new Insets(10, 10, 10, 0);
            this.setAlignment(Pos.CENTER_LEFT);
            this.getStyleClass().add("memo_item");
            this.setPadding(insets);

            this.typeLabel = new Label(this.memo.getItemType().getCnTypeStr());
            this.frLabel = new Label(this.memo.getFrom().toString());
            this.toLabel = new Label(this.memo.getTo().toString());
            this.descLabel = new Label("内容: " + this.memo.getDetailText());
            this.statusLabel = new Label(Const.STATUS_STRING[this.memo.getStatus()]);

            this.typeLabel.getStyleClass().addAll("item_title", "label");
            this.descLabel.getStyleClass().addAll("item_desc", "label");
            this.frLabel.getStyleClass().addAll("item_date", "label");
            this.toLabel.getStyleClass().addAll("item_date", "label");
            this.statusLabel.getStyleClass().addAll("item_status", "label");
            HBox titleHBox = new HBox();
            titleHBox.setSpacing(20);
            titleHBox.getChildren().addAll(typeLabel, statusLabel);
            titleHBox.setAlignment(Pos.CENTER_LEFT);
            titleHBox.setPadding(insets);
            HBox hBox = new HBox();
            hBox.getChildren().addAll(frLabel, new Label(" ~ "), toLabel);
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(insets);

            this.getChildren().addAll(titleHBox, hBox, descLabel);
        }
    }
}
