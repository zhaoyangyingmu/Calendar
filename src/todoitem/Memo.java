package todoitem;

import todoitem.util.TimeStamp;

public class Memo {
    private TimeStamp from;
    private TimeStamp to;
    private String detailText;
    private ItemType itemType;
    public Memo(TimeStamp from, TimeStamp to, String detailText, ItemType itemType) {
        this.from = from;
        this.to = to;
        this.detailText = detailText;
        this.itemType = itemType;
    }

    public boolean isDuringTime(TimeStamp from, TimeStamp to){
        if ((this.from.isBefore(to)) && (this.to.isAfter(from))) {
            return true;
        }
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

        public static ItemType parseItemType(String typeStr){
            switch (typeStr) {
                case "LEISURE":
                    return LEISURE;
                case "DATING":
                    return DATING;
                case "STUDY":
                    return STUDY;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object object) {
        Memo memo = (Memo)object;
        return (from.equals(memo.getFrom())) && (to.equals(memo.getTo()))
                && (detailText.equals(memo.getDetailText())) && (itemType == memo.getItemType());
    }

    @Override
    public String toString() {
        return "item";
    }
}
