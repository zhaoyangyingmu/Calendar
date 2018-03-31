package ui;


import javafx.geometry.Insets;

public class Config {
    private static final int rectangleHeight = 50;
    private static final int rectangleWidth = 70;

    private static final int vGap = 5;//for content grid
    private static final int hGap = 6;//for content grid

    private static final int stageVPadding = 10;
    private static final int stageHPadding = 20;
    private static final int windowWidth = 568;
    private static final int windowHeight = 460;

    public static int getWindowWidth(){
        return windowWidth;
    }
    public static int getWindowHeight() {
        return windowHeight;
    }

    public static int getRectangleWidth() {
        return rectangleWidth;
    }
    public static int getRectangleHeight() {
        return rectangleHeight;
    }

    public static int getvGap() {
        return vGap;
    }
    public static int gethGap() {
        return hGap;
    }

    public static Insets getPadding() {
        return new Insets(stageVPadding,stageHPadding,stageVPadding,stageHPadding);
    }

}
