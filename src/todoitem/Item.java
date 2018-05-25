package todoitem;

import todoitem.util.TimeStamp;

import java.io.Serializable;

public abstract class Item implements Serializable, ItemInterface, AttributeMap {
    private TimeStamp from;
    private TimeStamp to;
    private ItemType itemType;
    private String detailText;

    public Item(TimeStamp from, TimeStamp to, String detailText, ItemType itemType,
                int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, itemType, priority, status, isFather, false, 60, true, 5);
    }

    public Item(TimeStamp from, TimeStamp to, String detailText, ItemType itemType,
                int priority, int status, boolean isFather,
                boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        if (to == null || from == null) {
            throw new Exception("to or from TimeStamp is null! ");
        }
        if ((!from.isValid()) || (!to.isValid()) || !from.isBefore(to)) { // from  strictly after to
            throw new Exception("Time is invalid");
        }
        addAttr("startTime", from.toString());//开始时间
        addAttr("endTime", to.toString());  //结束时间
        addAttr("type", itemType.typeStr);  //待办事项类型
        addAttr("priority", "" + priority); //优先级
        addAttr("status", "" + status);     //完成进度
        addAttr("isFather", "" + isFather); //是否是父待办事项
        addAttr("fatherID", "0");  // 默认此为父待办事项，因此不存在父待办事项，即父待办事项ID为0

        /*
        *提醒
        **/
        addAttr("promptStatus", "" + promptStatus);    //是否进行提醒，默认不提醒
        addAttr("minutesAhead", "" + ahead);  //提前多久进行提醒，默认提前一小时
        addAttr("showOnStage", "" + showOnStage);  //是否在主界面区域显示，默认显示
        addAttr("minutesDelta", "" + delta);    //多久提醒一次，默认5分钟
        this.from = from;
        this.to = to;
        this.detailText = detailText;
        this.itemType = itemType;
    }


    @Override
    public boolean isDuringTime(TimeStamp from, TimeStamp to) {
        if ((this.from.isBefore(to)) && (this.to.isAfter(from))) {
            return true;
        }
        return false;
    }

    @Override
    public TimeStamp getFrom() {
        return from;
    }

    @Override
    public void setFrom(TimeStamp from) {
        this.from = from;
    }

    @Override
    public TimeStamp getTo() {
        return to;
    }

    @Override
    public void setTo(TimeStamp to) {
        this.to = to;
    }

    @Override
    public String getDetailText() {
        return detailText;
    }

    @Override
    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }


    public enum ItemType {
        OTHER("other"), MEETING("meeting"), APPOINTMENT("appointment");
        private String typeStr;

        ItemType(String typeStr) {
            this.typeStr = typeStr;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public static ItemType parseItemType(String typeStr) {
            switch (typeStr) {
                case "MEETING":
                    return MEETING;
                case "APPOINTMENT":
                    return APPOINTMENT;
                case "OTHER":
                    return OTHER;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object object) {
        Item item = (Item) object;
        return (from.equals(item.getFrom())) && (to.equals(item.getTo()))
                && (detailText.equals(item.getDetailText())) && (itemType == item.getItemType());
    }

    @Override
    public String toString() {
        return "item";
    }
}
