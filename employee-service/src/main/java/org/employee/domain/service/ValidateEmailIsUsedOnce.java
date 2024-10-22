package org.employee.domain.service;

import lombok.AllArgsConstructor;
import org.employee.domain.exception.EmailExistBeforeException;
import org.employee.domain.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateEmailIsUsedOnce {

    private final EmployeeRepository employeeRepository;

    public void validateEmailIsNotUsedBefore(String email) {
        employeeRepository.findByEmail(email).ifPresent(employee -> {
            throw new EmailExistBeforeException(email);
        });
    }
}
