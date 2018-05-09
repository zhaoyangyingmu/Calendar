package todoitem.itemSub;

import todoitem.Memo;
import todoitem.util.TimeStamp;

/**
 * Created by Bing Chen on 2018/5/9.
 */
public class Appointment extends Memo {
    private String participants;
    private String location;
    public Appointment(TimeStamp from, TimeStamp to, String detailText, ItemType itemType,String participants,String location) {
        super(from, to, detailText, itemType);
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
