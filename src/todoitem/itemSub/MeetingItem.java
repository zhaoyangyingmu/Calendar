package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class MeetingItem extends Item {
    private String topic;
    private String location;
    public MeetingItem(TimeStamp from, TimeStamp to, String detailText, String topic, String location) throws Exception {
        super(from, to, detailText, ItemType.MEETING);
        this.topic=topic;
        this.location=location;
    }

    public String getTopic() {
        return topic;
    }

//    public void setTopic(String topic) {
//        this.topic = topic;
//    }

    public String getLocation() {
        return location;
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }
}
