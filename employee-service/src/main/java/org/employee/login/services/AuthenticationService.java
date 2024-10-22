package org.employee.login.services;

import lombok.AllArgsConstructor;
import org.employee.domain.model.Employee;
import org.employee.domain.repositories.EmployeeRepository;
import org.employee.login.dto.LoggedInUser;
import org.employee.login.dto.LoginResponse;
import org.employee.login.dto.LoginUserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public Employee getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().
                getAuthentication();
        LoggedInUser user = (LoggedInUser) authentication.getPrincipal();

        return user.getEmployee();
    }

    public LoginResponse authenticateAndGetToken(LoginUserDto loginUserDto) {
        Employee authenticatedUser = authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        return new LoginResponse().setToken(jwtToken).
                setExpiresIn(jwtService.getExpirationTime());
    }

    private Employee authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return employeeRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
