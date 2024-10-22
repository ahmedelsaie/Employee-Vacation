package org.employee.serrvices;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.employee.domain.model.Employee;
import org.employee.domain.model.TimeOffRequest;
import org.employee.domain.repositories.RequestCategoryRepository;
import org.employee.domain.repositories.RequestsRepository;
import org.employee.domain.service.PreviousRequestRuleValidater;
import org.employee.dto.TimeOffRequestDto;
import org.employee.dto.VacationRequestDto;
import org.employee.utils.timeZone.DateUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VacationRequestService {

    private final EmployeeService employeeService;

    private final RequestsRepository requestsRepository;

    private final RequestCategoryRepository requestCategoryRepository;

    private final PreviousRequestRuleValidater previousRules;

    @Transactional
    public TimeOffRequestDto requestLeave(UUID employeeId,
                                          VacationRequestDto vacationRequestDto) {

        TimeOffRequest timeOffRequest = constructRequest(employeeId, vacationRequestDto);
        List<TimeOffRequest> previousRequests = getPreviousRequests(vacationRequestDto);
        previousRules.check(timeOffRequest, previousRequests);
        TimeOffRequest savedTimeOffRequest = requestsRepository.save(timeOffRequest);
        return constructDto(savedTimeOffRequest);
    }

    private TimeOffRequest constructRequest(UUID employeeId, VacationRequestDto vacationRequestDto) {
        Employee employee = employeeService.get(employeeId);
        requestsRepository.findAllByStartDateBetween(vacationRequestDto.getStartDate(),
                vacationRequestDto.getEndDate());

        TimeOffRequest timeOffRequest = new TimeOffRequest();
        timeOffRequest.setEmployee(employee);
        timeOffRequest.setStartDate(vacationRequestDto.getStartDate());
        timeOffRequest.setEndDate(vacationRequestDto.getEndDate());

        timeOffRequest.setRequestCategory(requestCategoryRepository.
                findOneByRequestCategoryType(vacationRequestDto.getRequestCategoryType()));

        return timeOffRequest;
    }

    private List<TimeOffRequest> getPreviousRequests(VacationRequestDto vacationRequestDto) {
        var x = requestsRepository.
                findAllByStartDateBetween(vacationRequestDto.getStartDate(), vacationRequestDto.getEndDate());

        var y = requestsRepository.
                findAllByEndDateBetween(vacationRequestDto.getStartDate(), vacationRequestDto.getEndDate());


        List<TimeOffRequest> previousRequests = new ArrayList<>();
        previousRequests.addAll(x);
        previousRequests.addAll(y);

        return previousRequests;
    }

    private TimeOffRequestDto constructDto(TimeOffRequest savedTimeOffRequest) {
        return new TimeOffRequestDto(savedTimeOffRequest.getId(), savedTimeOffRequest.
                getRequestCategory().getRequestCategoryType(),
                employeeService.convertToDto(savedTimeOffRequest.getEmployee()),
                DateUtils.parseDate(savedTimeOffRequest.getStartDate()),
                DateUtils.parseDate(savedTimeOffRequest.getEndDate()));
    }

}
