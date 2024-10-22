package org.employee.utils.timeZone;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String parseDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(CurrentTimeZone.getCurrentTimeZone());
        return sdf.format(date);
    }
}
