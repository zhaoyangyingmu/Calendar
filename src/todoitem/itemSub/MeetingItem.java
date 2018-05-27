package todoitem.itemSub;

import todoitem.Const;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class MeetingItem extends Item {
    public MeetingItem(HashMap<String, String> attrs) {
        super(attrs);
    }

    public MeetingItem(TimeStamp from, TimeStamp to, String detailText, String topic, String location) throws Exception {
        /*
         * 默认优先级为4，即不重要 & 不紧急
         * 默认状态为1， 即未开始
         * 默认为父待办事项
         */
        this(from, to, detailText, topic, location, Const.PRIORITY, Const.STATUS, Const.IS_FATHER);
    }

    public MeetingItem(TimeStamp from, TimeStamp to, String detailText, String topic, String location,
                       int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, topic, location, priority, status, isFather,
                Const.PROMPT_STATUS, Const.MINUTES_AHEAD, Const.SHOW_ON_STAGE, Const.MINUTES_DELTA);
    }

    public MeetingItem(TimeStamp from, TimeStamp to, String detailText, String topic, String location,
                       int priority, int status, boolean isFather, boolean promptStatus,
                       long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.MEETING, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("place", location);
        addAttr("topic", topic);
    }

    public String getTopic() {
        return getValue("topic");
    }

//    public void setTopic(String topic) {
//        this.topic = topic;
//    }

    public String getLocation() {
        return getValue("place");
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }
}
