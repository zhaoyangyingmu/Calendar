package todoitem;

import todoitem.itemSub.*;

import java.util.HashMap;

public class ItemFactory {
    static Item createItemByItemType(Item.ItemType type, HashMap<String, String> attrs) {
        switch (type) {
            case DATE:
                return new AppointmentItem(attrs);
            case MEETING:
                return new MeetingItem(attrs);
            case CUSTOM:
                return new OtherItem(attrs);
            case ANNIVERSARY:
                return new AnniversaryItem(attrs);
            case TRAVEL:
                return new TravelItem(attrs);
            case COURSE:
                return new CourseItem(attrs);
            case INTERVIEW:
                return new InterviewItem(attrs);
            default:
                return new OtherItem(attrs);
        }
    }
}
