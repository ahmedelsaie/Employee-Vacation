package org.employee.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.employee.dto.CreateEmployeeDTo;
import org.employee.dto.EmployeeDto;
import org.employee.login.dto.LoginResponse;
import org.employee.login.dto.LoginUserDto;
import org.employee.login.services.AuthenticationService;
import org.employee.serrvices.EmployeeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Validated
@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final EmployeeService employeeService;

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public EmployeeDto createEmployee(@RequestBody @Valid CreateEmployeeDTo createEmployeeDTo) {
        return employeeService.createEmployee(createEmployeeDTo);
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody @Valid LoginUserDto loginUserDto) {
        return authenticationService.authenticateAndGetToken(loginUserDto);
    }
}
