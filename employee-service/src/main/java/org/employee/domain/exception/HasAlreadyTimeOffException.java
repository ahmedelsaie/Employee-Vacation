package org.employee.domain.exception;

import org.employee.domain.model.TimeOffRequest;
import org.employee.utils.timeZone.DateUtils;

public class HasAlreadyTimeOffException extends RuntimeException {
    public HasAlreadyTimeOffException(TimeOffRequest current, TimeOffRequest previous) {
        super(createMessage(current, previous));
    }

    private static String createMessage(TimeOffRequest current, TimeOffRequest previous) {
        String msg = "can't plan a time off at the given date " + DateUtils.parseDate(current.getStartDate()) + ", "
                + DateUtils.parseDate(current.getEndDate());
        msg += ", because another request is there " + DateUtils.parseDate(previous.getStartDate()) + ", "
                + DateUtils.parseDate(previous.getEndDate());
        return msg;
    }
}
