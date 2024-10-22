package org.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@ToString
@Getter
public class EmployeeDto {

    private final UUID id;

    private final String name;

    private final String position;

    private final String email;

    private final float salary;

    private final String createdAt;

    private final String updatedAt;
}
