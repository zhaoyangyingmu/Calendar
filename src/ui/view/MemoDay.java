package ui.view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import todoitem.Memo;

import java.util.ArrayList;
import java.util.Set;

/**
 * 2018-05-08
 *
 * @author Jian Zhang
 */

public class MemoDay extends Special {
    private ArrayList<Memo> memos;
    private Set<Memo.ItemType> types;

    MemoDay(DayItem item, ArrayList<Memo> memos) {
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
        setTypes();
        setStyleClass();
        paint();
    }

    @Override
    protected void setStyleClass() {
        for (Memo.ItemType type : types) {
            this.getStyleClass().add(type.getTypeStr());
        }
    }

    @Override
    protected void paint() {
        HBox hBox = new HBox();
        for (Memo.ItemType type : types) {
            Label label = new Label();
            //TODO  label属性
            label.getStyleClass().add("type_" + type.getTypeStr());
            hBox.getChildren().add(label);
        }
        this.setTop(hBox);
    }
}
