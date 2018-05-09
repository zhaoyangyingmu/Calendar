package ui.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import todoitem.Memo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */

public class MemoDay extends Special {
    private ArrayList<Memo> memos;
    private Set<Memo.ItemType> types;

    public MemoDay(DayItem item, ArrayList<Memo> memos) {
        super(item);
        this.memos = memos;
        init();
    }

    private void setTypes() {
        for (Memo memo : memos) {
            types.add(memo.getItemType());
        }
    }

    @Override
    protected void init() {
        types = new HashSet<>();
        setTypes();
        setStyleClass();
        paint();
    }

    @Override
    protected void setStyleClass() {
        for (Memo.ItemType type : types) {
            dayItem.addStyleClass(type.getTypeStr());
        }
    }

    @Override
    protected void paint() {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5, 5, 5, 5));
        for (Memo.ItemType type : types) {
            Circle circle = new Circle();
            circle.setRadius(4);
            circle.getStyleClass().add("type_" + type.getTypeStr());
            hBox.getChildren().add(circle);
        }
        dayItem.setTopNode(hBox);
    }
}
