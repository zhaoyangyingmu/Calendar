package database;

import org.junit.Test;
import todoitem.Item;
import todoitem.itemSub.*;
import todoitem.util.TimeStamp;

import java.util.HashMap;

import static org.junit.Assert.*;

public class MysqlTest {
    @Test
    public void totalTest() throws Exception {
        Mysql mysql = Mysql.getInstance();
        mysql.clear();
        //Anniversary
        TimeStamp from = TimeStamp.createStampDayStart(1801,1,1);
        TimeStamp to = TimeStamp.createStampDayEnd(1801,2,2);
        AnniversaryItem anniversaryItem = new AnniversaryItem(from , to ,"纪念日详情" , "结婚纪念日" );
        int anniId = mysql.addSchedule(anniversaryItem.getAttrs());
        anniversaryItem.setID(anniId); // 可能为0

        //AppointmentItem
        from = TimeStamp.createStampDayStart(1803 , 1, 1);
        to = TimeStamp.createStampDayEnd(1804, 1 , 1);
        AppointmentItem appointmentItem = new AppointmentItem(from, to , "今天约会" , "女票" , "五角场");
        int appointId = mysql.addSchedule(appointmentItem.getAttrs());
        appointmentItem.setID(appointId);

        //Course
        from = TimeStamp.createStampDayStart(1804, 1, 1);
        to = TimeStamp.createStampDayEnd(1804, 1,1);
        CourseItem courseItem = new CourseItem(from , to , "离散数学", "是一门早课", "陈老师",
                "课讲得不错", "2107", "7", "5");
        int courseId = mysql.addSchedule(courseItem.getAttrs());
        courseItem.setID(courseId);

        //Interview
        from = TimeStamp.createStampDayStart(1805, 1 , 1);
        to = TimeStamp.createStampDayEnd(1805, 1, 1);
        InterviewItem interviewItem = new InterviewItem(from, to, "张江高科", "张江高科技公司", "码农", "要记住的！");
        int interId = mysql.addSchedule(interviewItem.getAttrs());
        interviewItem.setID(interId);

        //MeetingItem
        from = TimeStamp.createStampDayStart(1810 , 1, 1);
        to = TimeStamp.createStampDayEnd(1811, 1, 1);
        MeetingItem meetingItem = new MeetingItem(from, to, "特大会议", "", "地点");
        int meetingId = mysql.addSchedule(meetingItem.getAttrs());
        meetingItem.setID(meetingId);

        //OtherItem
        from = TimeStamp.createStampDayStart(1810 , 1, 1);
        to = TimeStamp.createStampDayEnd(1811, 1, 1);
        OtherItem otherItem = new OtherItem(from , to , "34134");
        int otherId = mysql.addSchedule(otherItem.getAttrs());
        otherItem.setID(otherId);

        //TravelItem
        from = TimeStamp.createStampDayStart(1811 , 2, 1);
        to = TimeStamp.createStampDayEnd(1812, 1, 1);
        TravelItem travelItem = new TravelItem(from , to , "plane", "北京", "30", "旅游好玩");
        int travelId = mysql.addSchedule(travelItem.getAttrs());
        travelItem.setID(travelId);

        if(anniId != 0) {
            AnniversaryItem anniResult = new AnniversaryItem(mysql.queryByID(anniId+""));
            assertEquals(anniversaryItem.getAnniversaryType(), anniversaryItem.getAnniversaryType());
            if(mysql.deleteSchedule(anniId) != 0) {
                assertEquals(0, mysql.queryByID(anniId+"").size());
                anniId = 0;
            }
        }

        if(anniId == 0 && appointId!= 0 && courseId != 0 && interId != 0 && meetingId!= 0 && otherId != 0 && travelId!= 0) {
            from = TimeStamp.createStampDayStart(1800 , 1, 1);
            to = TimeStamp.createStampDayEnd(1812, 1, 1);
            assertEquals(6, mysql.queryByTime(from.toString() , to.toString()).size());
        }

        if(courseId != 0) {
            CourseItem courseResult = new CourseItem(mysql.queryByID(courseId+""));
            assertEquals(courseItem.getTeacher(), courseResult.getTeacher());
            if(mysql.deleteSchedule(courseId) != 0) {
                assertEquals(0, mysql.queryByID(courseId+"").size());
                courseId = 0;
            }
        }
        /**
         * 至此增删查的简单测试完毕
         * 现在开始增加子待办事项
         * */

        if(travelId != 0) {
            from = TimeStamp.createStampDayStart(1811 , 2, 1);
            to = TimeStamp.createStampDayEnd(1812, 1, 1);
            OtherItem otherItem1 = new OtherItem(from , to , "No details");
        }

    }
}