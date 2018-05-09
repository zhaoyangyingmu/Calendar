package todoitem;

import todoitem.util.TimeStamp;

public abstract class Item implements ItemInterface {
    private TimeStamp from;
    private TimeStamp to;
    private ItemType itemType;
    private String detailText;
    public Item(TimeStamp from, TimeStamp to, String detailText, ItemType itemType) {
        this.from = from;
        this.to = to;
        this.detailText = detailText;
        this.itemType = itemType;
    }

    @Override
    public boolean isDuringTime(TimeStamp from, TimeStamp to){
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
        Item item = (Item)object;
        return (from.equals(item.getFrom())) && (to.equals(item.getTo()))
                && (detailText.equals(item.getDetailText())) && (itemType == item.getItemType());
    }

    @Override
    public String toString() {
        return "item";
    }
}
