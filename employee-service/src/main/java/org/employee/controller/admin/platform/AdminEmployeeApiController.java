package org.employee.controller.admin.platform;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.employee.domain.model.Employee;
import org.employee.domain.repositories.EmployeeRepository;
import org.employee.dto.CreateEmployeeDTo;
import org.employee.dto.EmployeeDto;
import org.employee.dto.TimeOffRequestDto;
import org.employee.dto.VacationRequestDto;
import org.employee.serrvices.EmployeeService;
import org.employee.serrvices.VacationRequestService;
import org.employee.utils.validaters.ValidUuid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("admin-platform/employees")
@AllArgsConstructor
@Validated
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "admin-platform apis")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminEmployeeApiController {

    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    private final VacationRequestService vacationRequestService;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable
                                       @ValidUuid String id) {
        return employeeService.getEmployeeById(UUID.fromString(id));
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployeeById(@PathVariable
                                          @ValidUuid String id, @RequestBody @Valid CreateEmployeeDTo createEmployeeDTo) {
        return employeeService.getEmployeeById(UUID.fromString(id));
    }

    @PostMapping("/{id}/create-vacation")
    public TimeOffRequestDto createVacation(@PathVariable
                                            @ValidUuid String id, @RequestBody @Valid VacationRequestDto vacationRequestDto) {
        return vacationRequestService.requestLeave(UUID.fromString(id), vacationRequestDto);
    }

    @GetMapping
    public Iterable<Employee> getEmployeeById() {
        return employeeRepository.findAll();
    }
}
