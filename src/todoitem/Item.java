package todoitem;

import todoitem.util.TimeStamp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class Item implements Serializable, ItemInterface, AttributeMap {
    private HashMap<String, Object> attrsMap;

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
        attrsMap = new HashMap<>();
        addAttr("startTime", from);//开始时间
        addAttr("endTime", to);  //结束时间
        addAttr("content", detailText); //具体描述
        addAttr("type", itemType);  //待办事项类型
        addAttr("priority", priority); //优先级
        addAttr("status", status);     //完成进度
        addAttr("isFather", isFather); //是否是父待办事项
        addAttr("fatherID", "0");  // 默认此为父待办事项，因此不存在父待办事项，即父待办事项ID为0

        /*
        *提醒
        **/
        addAttr("promptStatus", promptStatus);    //是否进行提醒，默认不提醒
        addAttr("minutesAhead", ahead);  //提前多久进行提醒，默认提前一小时
        addAttr("showOnStage", showOnStage);  //是否在主界面区域显示，默认显示
        addAttr("minutesDelta", delta);    //多久提醒一次，默认5分钟
    }


    @Override
    public boolean isDuringTime(TimeStamp from, TimeStamp to) {
        return (((TimeStamp) getValue("startTime")).isBefore(to)) && (((TimeStamp) getValue("endTime")).isAfter(from));
    }

    @Override
    public TimeStamp getFrom() {
        return (TimeStamp) getValue("startTime");
    }

    @Override
    public void setFrom(TimeStamp from) {
        addAttr("startTime", from);
    }

    @Override
    public TimeStamp getTo() {
        return (TimeStamp) getValue("endTime");
    }

    @Override
    public void setTo(TimeStamp to) {
        addAttr("endTime", to);
    }

    @Override
    public String getDetailText() {
        return (String) getValue("content");
    }

    @Override
    public void setDetailText(String detailText) {
        addAttr("content", detailText);
    }

    @Override
    public ItemType getItemType() {
        return (ItemType) getValue("type");
    }

    @Override
    public void setItemType(ItemType itemType) {
        addAttr("type", itemType);
    }

    @Override
    public void removeAttr(String key) {
        if (attrsMap.containsKey(key))
            attrsMap.remove(key);
    }

    @Override
    public void addAttrs(Map<String, Object> attrs) {
        attrsMap.putAll(attrs);
    }

    @Override
    public void addAttr(String key, Object value) {
        if (attrsMap.containsKey(key))
            attrsMap.replace(key, value);
        else
            attrsMap.put(key, value);
    }

    @Override
    public HashMap<String, Object> getAttrs() {
        return attrsMap;
    }

    @Override
    public Object getValue(String key) {
        if (attrsMap.containsKey(key))
            return attrsMap.get(key);
        return null;
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
        return (((TimeStamp) getValue("startTime")).equals(item.getFrom())) && (((TimeStamp) getValue("endTime")).equals(item.getTo()))
                && (getValue("content").equals(item.getDetailText())) && (((ItemType) getValue("type")) == item.getItemType());
    }

    @Override
    public String toString() {
        return "item";
    }
}
