package todoitem;

import todoitem.util.TimeStamp;

public class Item {
    private TimeStamp from;
    private TimeStamp to;
    private String detailText;
    private ItemType itemType;
    public Item(TimeStamp from, TimeStamp to, String detailText, ItemType itemType) {
        this.from = from;
        this.to = to;
        this.detailText = detailText;
        this.itemType = itemType;
    }

    public boolean isDuringTime(TimeStamp from, TimeStamp to){
        return false;
    }

    public TimeStamp getFrom() {
        return from;
    }

    public void setFrom(TimeStamp from) {
        this.from = from;
    }

    public TimeStamp getTo() {
        return to;
    }

    public void setTo(TimeStamp to) {
        this.to = to;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }


    public enum ItemType {
        LEISURE("leisure"), DATING("dating"), STUDY("study");
        private String typeStr;

        ItemType(String typeStr) {
            this.typeStr = typeStr;
        }

        public String getTypeStr() {
            return typeStr;
        }
    }
}
