package amq.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {
    private static String DATE_FORMAT_PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        return df.format(date);
    }
}
