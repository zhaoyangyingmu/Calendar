package todoitem;

import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public interface ItemInterface {
    boolean isDuringTime(TimeStamp from, TimeStamp to);

    TimeStamp getFrom();

    void setFrom(TimeStamp from);

    TimeStamp getTo();

    void setTo(TimeStamp to);

    String getDetailText();

    void setDetailText(String detailText);

    Memo.ItemType getItemType();

    void setItemType(Memo.ItemType itemType);
}
