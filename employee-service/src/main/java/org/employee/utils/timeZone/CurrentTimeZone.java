package org.employee.utils.timeZone;

import java.util.TimeZone;

public class CurrentTimeZone {

    private static ThreadLocal<TimeZone> userContext = new ThreadLocal<>();

    public static void setCurrentTimeZone(TimeZone input) {
        userContext.set(input);
    }

    public static TimeZone getCurrentTimeZone() {
        return userContext.get();
    }
}
