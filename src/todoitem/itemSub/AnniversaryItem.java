package todoitem.itemSub;

import todoitem.Const;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class AnniversaryItem extends Item {
    public AnniversaryItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText, String anniversaryType) throws Exception {
        this(from, to, detailText, anniversaryType,  Const.PRIORITY, Const.STATUS, Const.IS_FATHER);

    }

    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText, String anniversaryType,
                           int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, anniversaryType, priority, status, isFather,
                Const.PROMPT_STATUS, Const.MINUTES_AHEAD, Const.SHOW_ON_STAGE, Const.MINUTES_DELTA);
    }

    public AnniversaryItem(TimeStamp from, TimeStamp to, String detailText, String anniversaryType, int priority, int status, boolean isFather,
                           boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.ANNIVERSARY, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("anniversaryType", anniversaryType);
        addAttr("startDay", from.toString());
    }

    public String getAnniversaryType() {
        return getValue("anniversaryType");
    }

    public String getStartDay() {
        return getValue("startDay");
    }
}
