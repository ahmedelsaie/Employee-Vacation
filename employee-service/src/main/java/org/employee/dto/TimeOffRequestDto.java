package org.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.employee.domain.model.RequestCategoryType;

@AllArgsConstructor
@ToString
@Getter
public class TimeOffRequestDto {

    private Long id;

    private RequestCategoryType requestCategoryType;

    private EmployeeDto employee;

    private String startDate;

    private String endDate;
}
