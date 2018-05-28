package todoitem.itemSub;

import todoitem.Const;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class TravelItem extends Item {
    public TravelItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String number, String remark) throws Exception {
        this(from, to, way, place, number, remark, Const.PRIORITY, Const.STATUS, Const.IS_FATHER);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String number, String remark, int priority) throws Exception {
        this(from, to, way, place, number, remark, priority, Const.STATUS, Const.IS_FATHER);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String number, String remark, int priority,
                      int status, boolean isFather) throws Exception {
        this(from, to, way, place, number, remark, priority, status, isFather,
                Const.PROMPT_STATUS, Const.MINUTES_AHEAD, Const.SHOW_ON_STAGE, Const.MINUTES_DELTA);
    }

    public TravelItem(TimeStamp from, TimeStamp to, String way, String place, String number, String remark, int priority,
                      int status, boolean isFather, boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, "", ItemType.TRAVEL, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        setWay(way);
        setPlace(place);
        setNumber(number);
        setRemark(remark);
    }

    public String getWay() {
        return getValue("way");
    }

    private void setWay(String way) {
        addAttr("way", way == null ? "" : way);
    }

    public String getPlace() {
        return getValue("place");
    }

    private void setPlace(String place) {
        addAttr("place", place == null ? "" : place);
    }

    public String getNumber() {
        return getValue("number");
    }

    private void setNumber(String number) {
        addAttr("number", number == null ? "" : number);
    }

    public String getRemark() {
        return getValue("remark");
    }

    private void setRemark(String remark) {
        addAttr("remark", remark == null ? "" : remark);
    }
}
