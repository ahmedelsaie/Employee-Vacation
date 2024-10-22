package org.employee.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
@Setter
@Getter
public class TimeOffRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RequestCategory requestCategory;

    @ManyToOne
    private Employee employee;

    private Date startDate;

    private Date endDate;

    public boolean isAnnualLeave() {
        return requestCategory.getRequestCategoryType() == RequestCategoryType.ANNUAL_LEAVE;
    }

    public boolean isWorkRemotely() {
        return requestCategory.getRequestCategoryType() == RequestCategoryType.WORK_REMOTELY;
    }
}
