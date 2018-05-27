package todoitem;

public enum AheadTimeType {
    ONEHOUR("提前一小时", 60) , FIVEHOURS("提前五小时" , 60*5) , ONEDAY("提前一天", 60*24) , ONEWEEK("提前一周" , 60*24*7);
    private String info;
    private long minutes;

    AheadTimeType(String info , long minutes) {
        this.info = info;
        this.minutes = minutes;
    }

    public String getInfo() {
        return info;
    }

    public long getMinutes() {
        return minutes;
    }

    public static AheadTimeType parseAheadType(String type) {
        switch(type) {
            case "提前一小时":
                return ONEHOUR ;
            case "提前五小时":
                return FIVEHOURS;
            case "提前一天" :
                return ONEDAY;
            case "提前一周":
                return ONEWEEK;
        }
        return ONEHOUR;
    }
}
