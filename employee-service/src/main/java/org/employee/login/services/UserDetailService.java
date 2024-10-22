package org.employee.login.services;

import lombok.AllArgsConstructor;
import org.employee.domain.model.Employee;
import org.employee.domain.repositories.EmployeeRepository;
import org.employee.login.dto.LoggedInUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Employee employee = employeeRepository.
                findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new LoggedInUser(employee);
    }
}
