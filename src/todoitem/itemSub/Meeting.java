package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class Meeting extends Item{
    private String topic;
    private String location;
    public Meeting(TimeStamp from, TimeStamp to, String detailText, ItemType itemType,String topic,String location) {
        super(from, to, detailText, itemType);
        this.topic=topic;
        this.location=location;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
