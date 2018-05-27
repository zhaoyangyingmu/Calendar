package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class InterviewItem extends Item {
    public InterviewItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public InterviewItem(TimeStamp from, TimeStamp to, String place, String company, String job, String remark) throws Exception {
        this(from, to, place, company, job, remark, 4, 1, true);
    }

    public InterviewItem(TimeStamp from, TimeStamp to, String place, String company, String job, String remark,
                         int priority, int status, boolean isFather) throws Exception {
        this(from, to, company, company, job, remark, priority, status, isFather, false, 60, true, 5);
    }

    public InterviewItem(TimeStamp from, TimeStamp to, String place, String company, String job, String remark,
                         int priority, int status, boolean isFather, boolean promptStatus,
                         long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, "", ItemType.INTERVIEW, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("place", place);
        addAttr("company", company);
        addAttr("job", job);
        addAttr("remark", remark);
    }

    public String getPlace() {
        return getValue("place");
    }

    public String getCompany() {
        return getValue("company");
    }

    public String getJob() {
        return getValue("job");
    }

    public String getRemark() {
        return getValue("remark");
    }
}
