package todoitem;

public interface AttributeFunc {
    int getPriority();

    int getStatus();

    int getID();

    int getFatherID();

    long minutesAhead();

    long minutesDelta();

    boolean isFather();

    boolean promptStatus();

    boolean showOnStage();

}
