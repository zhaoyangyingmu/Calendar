package todoitem.itemSub;

import todoitem.Memo;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class OtherMemo extends Memo {
    public OtherMemo(TimeStamp from, TimeStamp to, String detailText, ItemType itemType) {
        super(from, to, detailText, itemType);
    }
}
