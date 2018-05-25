package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class OtherItem extends Item {
    public OtherItem(TimeStamp from, TimeStamp to, String detailText) throws Exception {
        this(from, to, detailText, 4, 1, true);
    }

    public OtherItem(TimeStamp from, TimeStamp to, String detailText, int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, priority, status, isFather, false, 60, true, 5);
    }

    public OtherItem(TimeStamp from, TimeStamp to, String detailText, int priority, int status, boolean isFather, boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.OTHER, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("description", detailText);
    }
}
