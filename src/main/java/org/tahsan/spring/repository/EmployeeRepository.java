package org.tahsan.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tahsan.spring.domain.Employees;

@Repository
public interface EmployeeRepository extends CrudRepository<Employees, Long> {
	
	Employees getByEmployeeId(Long employeeId);

}
