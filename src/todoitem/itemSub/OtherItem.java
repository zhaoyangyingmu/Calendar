package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class OtherItem extends Item {
    public OtherItem(TimeStamp from, TimeStamp to, String detailText) {
        super(from, to, detailText, ItemType.OTHER);
    }
}
