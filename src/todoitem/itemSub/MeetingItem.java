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
        /*
         * 默认优先级为4，即不重要 & 不紧急
         * 默认状态为1， 即未开始
         * 默认为父待办事项
         */
        this(from, to, detailText, topic, location, 4, 1, true);
    }

    public MeetingItem(TimeStamp from, TimeStamp to, String detailText, String topic, String location,
                       int priority, int status, boolean isFather) throws Exception {
        super(from, to, detailText, ItemType.MEETING, priority, status, isFather);
        addAttr("place", location);
        addAttr("topic", topic);
        addAttr("content", detailText);
        this.topic = topic;
        this.location = location;
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
