package org.employee.controller.employee.platform;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.employee.dto.CreateEmployeeDTo;
import org.employee.dto.EmployeeDto;
import org.employee.dto.TimeOffRequestDto;
import org.employee.dto.VacationRequestDto;
import org.employee.login.services.AuthenticationService;
import org.employee.serrvices.EmployeeService;
import org.employee.serrvices.VacationRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("employees-platform/employees/me")
@AllArgsConstructor
@Validated
@Tag(name = "employees-platform apis")
@SecurityRequirement(name = "Bearer Authentication")
@PreAuthorize("hasAuthority('EMPLOYEE')")
public class EmployeeApiController {
    private final EmployeeService employeeService;

    private final VacationRequestService vacationRequestService;

    private final AuthenticationService authenticationService;

    @GetMapping
    public EmployeeDto getEmployeeById() {
        UUID id = authenticationService.getCurrentAuthenticatedUser().getId();
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public EmployeeDto updateEmployeeById(@RequestBody @Valid CreateEmployeeDTo createEmployeeDTo) {
        UUID id = authenticationService.getCurrentAuthenticatedUser().getId();
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/create-vacation")
    public TimeOffRequestDto createVacation(@RequestBody @Valid VacationRequestDto vacationRequestDto) {
        UUID id = authenticationService.getCurrentAuthenticatedUser().getId();
        return vacationRequestService.requestLeave(id, vacationRequestDto);
    }
}
