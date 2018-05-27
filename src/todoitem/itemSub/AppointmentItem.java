package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class AppointmentItem extends Item {
    public AppointmentItem(HashMap<String, String> attrs) {
        super(attrs);
    }

    public AppointmentItem(TimeStamp from, TimeStamp to, String detailText, String participants, String location) throws Exception {
        this(from, to, detailText, participants, location, 4, 1, true);
    }

    public AppointmentItem(TimeStamp from, TimeStamp to, String detailText, String participants, String location
            , int priority, int status, boolean isFather) throws Exception {
        this(from, to, detailText, participants, location, priority, status, isFather, false, 60, true, 5);
    }

    public AppointmentItem(TimeStamp from, TimeStamp to, String detailText, String participants, String location,
                           int priority, int status, boolean isFather, boolean promptStatus,
                           long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.DATE, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("place", location);
        addAttr("people", participants);
    }

    public String getParticipants() {
        return getValue("people");
    }

//    public void setParticipants(String participants) {
//        this.participants = participants;
//    }

    public String getLocation() {
        return getValue("place");
    }

//    public void setLocation(String location) {
//        this.location = location;
//    }
}
