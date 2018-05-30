package todoitem;


public class Const {
    public final static int BEFORE_BEGINNING = 0;   //未开始
    public final static int IN_PROGRESS = 1;        //进行中
    public final static int COMPLETED = 2;          //已完成
    public final static int OVERDUE = 3;            //过期
    public final static int PRIORITY = 4;           //默认优先级为4，即不重要 & 不紧急
    public final static int STATUS = BEFORE_BEGINNING;  //默认状态为0， 即未开始
    public final static int FATHER_ID = 0;
    public final static int ID = -1;
    public final static long MINUTES_AHEAD = 60;        //默认提前一小时
    public final static long MINUTES_DELTA = 5;         //默认每5五分钟提醒一次
    public final static boolean IS_FATHER = true;       //默认为父待办事项
    public final static boolean PROMPT_STATUS = false;  //默认不提醒
    public final static boolean SHOW_ON_STAGE = true;   //默认显示在界面上
    public final static String STATUS_STRING[] = new String[]{
            "未开始", "进行中", "已完成", "过期"
    };

}
