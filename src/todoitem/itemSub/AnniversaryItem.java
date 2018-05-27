package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

public class AnniversaryItem extends Item {
    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText, String anniversaryType) throws Exception {
        this(from,to,detailText,anniversaryType,4,1,true);

    }
    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText,String anniversaryType,
                           int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, anniversaryType,priority, status, isFather,
                false, 60, true, 5);
    }

    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText,String anniversaryType, int priority, int status, boolean isFather,
                           boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.ANNIVERSARY, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("anniversaryType",anniversaryType);
        addAttr("startDay",from.toString());
    }
}
