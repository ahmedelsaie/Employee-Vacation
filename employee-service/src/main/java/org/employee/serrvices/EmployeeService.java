package org.employee.serrvices;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.employee.domain.model.Employee;
import org.employee.domain.model.EmployeeRole;
import org.employee.domain.repositories.EmployeeRepository;
import org.employee.domain.service.ValidateEmailIsUsedOnce;
import org.employee.dto.CreateEmployeeDTo;
import org.employee.dto.EmployeeDto;
import org.employee.exception.EntityNotFoundException;
import org.employee.utils.timeZone.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final ValidateEmailIsUsedOnce validateEmailIsUsedOnce;

    @Transactional
    public EmployeeDto createEmployee(CreateEmployeeDTo createEmployeeDTo) {
        Employee employee = new Employee();
        modelMapper.map(createEmployeeDTo, employee);
        employee.setPassword(passwordEncoder.encode(createEmployeeDTo.getPassword()));
        validateEmailIsUsedOnce.validateEmailIsNotUsedBefore(createEmployeeDTo.getEmail());
        employee.setEmployeeRole(EmployeeRole.EMPLOYEE);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    @Transactional
    public EmployeeDto updateEmployee(UUID id, CreateEmployeeDTo updateEmployeeDto) {
        Employee employee = get(id);
        modelMapper.map(updateEmployeeDto, employee);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }

    public EmployeeDto getEmployeeById(UUID id) {
        return convertToDto(get(id));
    }

    public Employee get(UUID id) {
        return employeeRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(Employee.class, "id", id.toString()));
    }

    public EmployeeDto convertToDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName(),
                employee.getPosition(), employee.getEmail(),
                employee.getSalary(), DateUtils.parseDate(employee.getCreatedAt()),
                DateUtils.parseDate(employee.getUpdatedAt()));
    }

}
