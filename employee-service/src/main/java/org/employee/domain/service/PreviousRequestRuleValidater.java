package org.employee.domain.service;

import org.employee.domain.exception.HasAlreadyTimeOffException;
import org.employee.domain.model.TimeOffRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreviousRequestRuleValidater {

    public void check(TimeOffRequest timeOffRequest, List<TimeOffRequest> previousOverLappingRequests) {
        if (timeOffRequest.isAnnualLeave()) {
            checkAnnualLeavePolicy(timeOffRequest, previousOverLappingRequests);
        } else {
            checkGeneralLeavePolicy(timeOffRequest, previousOverLappingRequests);
        }
    }

    private void checkGeneralLeavePolicy(TimeOffRequest timeOffRequest, List<TimeOffRequest> previousRequests) {
        if (!previousRequests.isEmpty()) {
            throw new HasAlreadyTimeOffException(timeOffRequest, previousRequests.get(0));
        }
    }

    private void checkAnnualLeavePolicy(TimeOffRequest timeOffRequest, List<TimeOffRequest> previousRequests) {
        previousRequests.stream()
                .filter(e -> !e.isWorkRemotely())
                .findFirst().ifPresent(previousRequest -> {
                    throw new HasAlreadyTimeOffException(timeOffRequest, previousRequest);
                });
    }
}
