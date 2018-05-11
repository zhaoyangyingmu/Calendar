package kernel;

import io.ItemIO;
import javafx.application.Application;

/*
* Start here!
*
* */
public class Main {
    public static void main(String[] args) {
        ItemIO.input();
        Application.launch(Display.class,args);
    }
}
