package todoitem;

public enum TimeInterType {
    FIVEMINUTES("每五分钟" , 5), ONEHOUR("每小时" , 60) , ONEDAY("每天" , 60 * 24);
    private String info;
    private long minutes;

    TimeInterType(String info , long minutes) {
        this.info = info;
        this.minutes = minutes;
    }

    public String getInfo() {
        return info;
    }

    public long getMinutes() {
        return minutes;
    }

    public static TimeInterType parseInterType(String type) {
        switch(type) {
            case "每五分钟":
                return FIVEMINUTES ;
            case "每小时":
                return ONEHOUR;
            case "每天" :
                return ONEDAY;
        }
        return FIVEMINUTES;
    }
}
