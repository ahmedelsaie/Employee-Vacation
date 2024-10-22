package org.employee.domain.repositories;

import org.employee.domain.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);
}
