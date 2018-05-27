package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class TravelItem extends Item {
    public TravelItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String numnber, String remark) throws Exception {
        this(from, to, way, place, numnber, remark, 4, 1, true);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String numnber, String remark, int priority,
                      int status, boolean isFather) throws Exception {
        this(from, to, way, place, numnber, remark, priority, status, isFather, false, 60, true, 5);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String number, String remark, int priority,
                      int status, boolean isFather, boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, "", ItemType.TRAVEL, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("way", way);
        addAttr("place", place);
        addAttr("number", number);
        addAttr("remark", remark);
    }

    public String getWay() {
        return getValue("way");
    }

    public String getPlace() {
        return getValue("place");
    }

    public String getNumber() {
        return getValue("number");
    }

    public String getRemark() {
        return getValue("remark");
    }
}
