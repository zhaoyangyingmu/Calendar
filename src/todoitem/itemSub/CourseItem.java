package todoitem.itemSub;

import todoitem.Item;
import todoitem.util.TimeStamp;

import java.util.HashMap;

public class CourseItem extends Item {
    public CourseItem(HashMap<String, String> attrsMap) {
        super(attrsMap);
    }

    public CourseItem(TimeStamp from, TimeStamp to, String name, String detailText, String duration, String teacher,
                      String remark, String place, String day) throws Exception {
        this(from, to, name, detailText, duration, teacher, remark, place, day, 4, 1, true);
    }

    public CourseItem(TimeStamp from, TimeStamp to, String name, String detailText, String duration, String teacher,
                      String remark, String place, String day, int priority, int status, boolean isFather) throws Exception {
        this(from, to, name, detailText, duration, teacher, remark, place, day, priority, status, isFather,
                false, 60, true, 5);
    }

    public CourseItem(TimeStamp from, TimeStamp to, String name, String detailText, String duration, String teacher,
                      String remark, String place, String day, int priority, int status, boolean isFather,
                      boolean promptStatus, long ahead, boolean showOnStage, long delta) throws Exception {
        super(from, to, detailText, ItemType.COURSE, priority, status, isFather, promptStatus, ahead, showOnStage, delta);
        addAttr("name", name);
        addAttr("startDay", from.toString());
        addAttr("duration", duration);
        addAttr("teacher", teacher);
        addAttr("remark", remark);
        addAttr("place", place);
        addAttr("day", day);
    }

    public String getName() {
        return getValue("name");
    }

    public String getStartDay() {
        return getValue("startDay");
    }

    public String getDuration() {
        return getValue("duration");
    }

    public String getTeacher() {
        return getValue("teacher");
    }

    public String getRemark() {
        return getValue("remark");
    }

    public String getPlace() {
        return getValue("place");
    }

    public String getDay() {
        return getValue("day");
    }
}
