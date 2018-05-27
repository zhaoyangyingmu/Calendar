package kernel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.effect.GaussianBlur;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import todoitem.Item;
import todoitem.itemSub.AppointmentItem;
import todoitem.util.TimeStamp;
import ui.Config;
import ui.pane.*;
import ui.util.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * You need to implement Calendar GUI here!
 * show the calendar of month of today.
 * jump to last/next month's calendar
 * jump to last/next year's calendar
 * <p>
 * jump to one specific day's calendar
 */
public class Display extends Application {
    private static Stage stage = new Stage();
    private static Pane imageCalendarPane = new Pane();
    private static GridPane calendarPane = new GridPane();
    private static DetailPane detailPane;
    private static TimeStamp fromStatic;
    private static TimeStamp toStatic;
    private static ImageView backgroundImage;
    private static EditPane editPane;
    private static boolean hasEdit = false;
    private static volatile boolean isClosed = false;
    private static PromptPane promptPane = PromptPane.getInstance();
    private static boolean hasPrompt = false;
    private static PromptSetPane promptSetPane = PromptSetPane.getInstance();
    private static boolean hasPromptSet = false;

    public Display() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        calendarPane.add(MenuPane.getInstance(), 0, 0);
        calendarPane.add(BodyPane.getInstance(), 0, 1);
        calendarPane.add(QueryPane.getInstance(), 0, 2);
        calendarPane.setMargin(QueryPane.getInstance(), new Insets(10, 0, 10, 0));

        final int calendarXVal = 550;
        final int calendarYVal = 40;
        calendarPane.setTranslateX(calendarXVal);
        calendarPane.setTranslateY(calendarYVal);

        backgroundImage = new ImageView(Config.class.getResource("/res/" + DateUtil.getToday().getMonth() + ".jpg").toString());
        backgroundImage.setFitHeight(Config.getWindowHeight());
        backgroundImage.setFitWidth(Config.getWindowWidth());
        backgroundImage.setEffect(new GaussianBlur(40));
        imageCalendarPane.getChildren().add(backgroundImage);
        imageCalendarPane.getChildren().add(calendarPane);
        addPromptPane();


        Scene scene = new Scene(imageCalendarPane, Config.getWindowWidth(), Config.getWindowHeight());
        stage.setMaxHeight(Config.getWindowHeight());
        stage.setMinHeight(Config.getWindowHeight());
        stage.setMaxWidth(Config.getWindowWidth());
        stage.setMinWidth(Config.getWindowWidth());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            isClosed = true;
        });
        setPrompt();
    }

    public static void addDetailPane(TimeStamp from, TimeStamp to) {
        if (hasEdit) {
            return;
        }
        if (detailPane != null) {
            removeDetailPane();
        }
        detailPane = new DetailPane(from, to);
        fromStatic = from;
        toStatic = to;
        imageCalendarPane.getChildren().add(detailPane);
    }

    public static void removeDetailPane() {
        imageCalendarPane.getChildren().remove(detailPane);
        detailPane = null;
    }

    public static void refreshDetailPane() {
        imageCalendarPane.getChildren().remove(detailPane);
        detailPane = new DetailPane(fromStatic, toStatic);
        imageCalendarPane.getChildren().add(detailPane);
    }

    public static void addEditPane(Item item, boolean fromAdd) {
        editPane = new EditPane(item, fromAdd);
        imageCalendarPane.getChildren().add(editPane);
        hasEdit = true;
        editPane.setAlignment(Pos.CENTER);
    }

    public static void removeEditPane() {
        BodyPane.getInstance().refresh();
        imageCalendarPane.getChildren().remove(editPane);
        hasEdit = false;
    }

    public static void addSearchPane() {
        imageCalendarPane.getChildren().add(SearchPane.getInstance());
        SearchPane.getInstance().setAlignment(Pos.CENTER);
    }

    public static void removeSearchPane() {
        imageCalendarPane.getChildren().remove(SearchPane.getInstance());
    }

    public static void addPromptPane() {
        if (!hasPrompt) {
            imageCalendarPane.getChildren().add(promptPane);
            hasPrompt = true;
        }
    }

    public static void removePromptPane() {
        if (hasPrompt) {
            imageCalendarPane.getChildren().remove(promptPane);
            hasPrompt = false;
        }
    }

    public static void addPromptSetPane() {
        if (!hasPromptSet) {
            imageCalendarPane.getChildren().add(promptSetPane);
            hasPromptSet = true;
        }
    }

    public static void removePromptSetPane() {
        if(hasPromptSet) {
            imageCalendarPane.getChildren().remove(promptSetPane);
            hasPromptSet = false;
        }
    }

    /**
     * paint the days of whole current month on the frame with the given kernel.CalendarDate
     *
     * @param date a valid kernel.CalendarDate param.
     */
    public static boolean paintDays(CalendarDate date) {
        if (date == null) {
            System.out.println("date is null!");
            return false;
        }
        if (DateUtil.isConsidered(date)) {
            MenuPane.getInstance().changeChoice(date);
            BodyPane.getInstance().changeContent(date);
            backgroundImage.setImage(new Image(Config.class.getResource("/res/" + date.getMonth() + ".jpg").toString()));
            return true;
        } else {
            System.out.println("date " + date.getYear() + date.getMonth() + date.getDay() + " is not valid");
            return false;
        }
    }

    public static void showToast(String mes) {
        int delay = 3500;
        int fadeInt = 500;
        int fadeOut = 500;
        Toast.makeText(stage, mes, delay, fadeInt, fadeOut);
    }

    public static void setPrompt() {
//        long time = 60 * 1000;

//
//        Thread thread = new Thread(() -> {
//            try {
//                while (true) {
//                    Thread.sleep(time);
////                    ArrayList<Item> items = new ArrayList<>();
////                    if (items.size() != 0) {
////
////                    }
////                    TimeStamp from = new TimeStamp(2018, 5, 26, 16, 25);
////                    TimeStamp to = new TimeStamp(2018, 5, 27, 17, 33);
////                    Item dating = new AppointmentItem(from, to, "今天，我有个约会", "女票", "邯郸");
////                    PromptPane.getInstance().setItem(dating);
////                    addPromptPane();
//                    Platform.runLater(run);
//
//                    if (isClosed) {
//                        break;
//                    }
//                }
//            } catch (Exception exp) {
//                exp.printStackTrace();
//            }
//        });
//        thread.start();
//        final Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println(System.currentTimeMillis());
//                Runnable run = new Runnable() {
//                    @Override
//                    public void run() {
//                        TimeStamp from = new TimeStamp(2018, 5, 26, 16, 25);
//                        TimeStamp to = new TimeStamp(2018, 5, 27, 17, 33);
//                        Item dating = null;
//                        try {
//                            dating = new AppointmentItem(from, to, "今天，我有个约会", "女票", "邯郸");
//                            PromptPane.getInstance().setItem(dating);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                // Platform 不再执行，当主线程关闭时。
//                Platform.runLater(run);
//                if (isClosed) {
//                    timer.cancel();
//                }
//            }
//        }, 1000, 1000);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
//        ScheduledFuture scheduledFuture = service.schedule()

        service.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis());
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    TimeStamp from = new TimeStamp(2018, 5, 26, 16, 25);
                    TimeStamp to = new TimeStamp(2018, 5, 27, 17, 33);
                    Item dating = null;
                    try {
                        dating = new AppointmentItem(from, to, "今天，我有个约会", "女票", "邯郸");
                        PromptPane.getInstance().setItem(dating);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            // Platform 不再执行，当主线程关闭时。
            Platform.runLater(run);
            if (isClosed) {
                service.shutdown();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }
}
