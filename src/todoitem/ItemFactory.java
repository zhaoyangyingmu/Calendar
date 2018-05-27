package todoitem;

import todoitem.itemSub.AppointmentItem;
import todoitem.itemSub.MeetingItem;
import todoitem.itemSub.OtherItem;

import java.util.HashMap;

public class ItemFactory {
    static Item createItemByItemType(Item.ItemType type, HashMap<String, String> attrs) {
        switch (type) {
            case APPOINTMENT:
                return new AppointmentItem(attrs);
            case MEETING:
                return new MeetingItem(attrs);
            case OTHER:
                return new OtherItem(attrs);
            default:
                return new OtherItem(attrs);
        }
    }
}
