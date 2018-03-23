import exception.InvalidDateException;

/*
* Start here!
*
* */
public class Main {
    public static void main(String[] args){
        /**
        System.out.println(Util.getCurrentYear());
        System.out.println(Util.getCurrentMonth());
        System.out.println(Util.getCurrentDay());
        */
        //todo
        try {
            Calendar c = new Calendar("1997-11-04");
        } catch (InvalidDateException e) {
            e.printStackTrace();
        }
    }
}
