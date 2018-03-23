import exception.InvalidDateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* You need to implement the Calendar's structures here!
*
* */
public class Calendar {
    public Calendar(String date) throws InvalidDateException {
        Pattern p = Pattern.compile("^{4}[0-9]-{2}[0-9]-{2}[0-9]$"); // left to be bettered.
        Matcher m = p.matcher(date);
        if (!m.find()){
            throw new InvalidDateException(date);
        }
        for (int i = 0 ; i < m.groupCount(); i++) {
            System.out.print(m.group(i));
        }
    }
}
