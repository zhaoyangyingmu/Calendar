package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class AppointmentItem extends Item {
    private String participants;
    private String location;
    public AppointmentItem(TimeStamp from, TimeStamp to, String detailText, String participants, String location) {
        super(from, to, detailText, ItemType.APPOINTMENT);
        this.participants = participants;
        this.location = location;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
