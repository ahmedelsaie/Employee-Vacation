package org.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.employee.domain.model.RequestCategoryType;

import java.util.Date;

@AllArgsConstructor
@ToString
@Getter
public class VacationRequestDto {

    private Date startDate;

    private Date endDate;

    private RequestCategoryType requestCategoryType;
}
