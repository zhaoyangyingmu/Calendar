package database;

import org.junit.Test;
import todoitem.itemSub.*;
import todoitem.util.TimeStamp;

import static org.junit.Assert.*;

public class MysqlTest {
    @Test
    public void totalTest() throws Exception {
        Mysql mysql = Mysql.getInstance();
        mysql.clear();
        //Anniversary
        TimeStamp from = TimeStamp.createStampDayStart(1801, 1, 1);
        TimeStamp to = TimeStamp.createStampDayEnd(1801, 2, 2);
        AnniversaryItem anniversaryItem = new AnniversaryItem(from, to, "张健", "纪念日详情", "结婚纪念日");
        int anniId = mysql.addSchedule(anniversaryItem.getAttrs());
        anniversaryItem.setID(anniId); // 可能为0

        //AppointmentItem
        from = TimeStamp.createStampDayStart(1803, 1, 1);
        to = TimeStamp.createStampDayEnd(1804, 1, 1);
        AppointmentItem appointmentItem = new AppointmentItem(from, to, "今天约会", "女票", "五角场");
        int appointId = mysql.addSchedule(appointmentItem.getAttrs());
        appointmentItem.setID(appointId);

        //Course
        from = TimeStamp.createStampDayStart(1804, 1, 1);
        to = TimeStamp.createStampDayEnd(1804, 1, 1);
        CourseItem courseItem = new CourseItem(from, to, "离散数学", "是一门早课", "陈老师",
                "课讲得不错", "2107", "7", "5");
        int courseId = mysql.addSchedule(courseItem.getAttrs());
        courseItem.setID(courseId);

        //Interview
        from = TimeStamp.createStampDayStart(1805, 1, 1);
        to = TimeStamp.createStampDayEnd(1805, 1, 1);
        InterviewItem interviewItem = new InterviewItem(from, to, "张江高科", "张江高科技公司", "码农", "要记住的！");
        int interId = mysql.addSchedule(interviewItem.getAttrs());
        interviewItem.setID(interId);

        //MeetingItem
        from = TimeStamp.createStampDayStart(1810, 1, 1);
        to = TimeStamp.createStampDayEnd(18011, 1, 1);
        MeetingItem meetingItem = new MeetingItem(from, to, "特大会议", "", "地点");


    }
}