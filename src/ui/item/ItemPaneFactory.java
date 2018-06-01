package ui.item;

import todoitem.Item;

public class ItemPaneFactory {
    public static ItemPane createPaneByItemType(Item item) {
        switch (item.getItemType()) {
            case COURSE:
                return new CourseItemPane(item);
            case CUSTOM:
                return new CustomItemPane(item);
            default:
                return null;
        }
    }
}
