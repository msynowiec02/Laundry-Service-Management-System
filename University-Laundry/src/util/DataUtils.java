package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String formatujDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}

