package org.tahsan.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tahsan.spring.domain.Employees;
import org.tahsan.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employees saveOrUpdateEmployee(Employees employee) {
		
//		if(employee.getStatus() == null || employee.getStatus() == "") {
//			employee.setStatus("TO_DO");
//		}
		
		return employeeRepository.save(employee);
	}
	

	@Cacheable(value = "employeesCache")
	public Iterable<Employees> getAllEmployees() {
		logger.debug("Fetching data from the database ...");
		return employeeRepository.findAll();
	}
	
	@Cacheable(value = "employeesCache", key = "#id")
	public Employees getEmployeeById(Long id) {
		logger.debug("Fetching data from the database for id : {} ...", id);
		return employeeRepository.getByEmployeeId(id);
		
	}
	
	public void deleteEmployee(Long id) {
		Employees employee = getEmployeeById(id);
		employeeRepository.delete(employee);
		
	}
	

}
