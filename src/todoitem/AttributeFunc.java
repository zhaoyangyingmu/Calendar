package todoitem;

public interface AttributeFunc {
    int getPriority();

    int getStatus();

    int getID();

    int getFatherID();

    long minutesAhead();

    void setMinutesAhead(long minutesAhead);

    long minutesDelta();

    void setMinutesDelta(long minutesDelta);

    boolean isFather();

    boolean promptStatus();

    void setPromptStatus(boolean promptStatus);

    boolean showOnStage();

    void setShowOnStage(boolean showOnStage);

}
