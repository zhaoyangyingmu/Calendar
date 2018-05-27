package todoitem.itemSub;

import todoitem.Const;
import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class InterviewItem extends Item {
    public InterviewItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public InterviewItem(TimeStamp from, TimeStamp to, String place, String company, String job, String remark) throws Exception {
        this(from, to, place, company, job, remark,  Const.PRIORITY, Const.STATUS, Const.IS_FATHER);
    }

    public InterviewItem(TimeStamp from, TimeStamp to, String place, String company, String job, String remark,
                         int priority, int status, boolean isFather) throws Exception {
        this(from, to, company, company, job, remark, priority, status, isFather,
                Const.PROMPT_STATUS, Const.MINUTES_AHEAD, Const.SHOW_ON_STAGE, Const.MINUTES_DELTA);
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