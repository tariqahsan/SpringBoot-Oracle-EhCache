package org.tahsan.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tahsan.spring.domain.Employees;
import org.tahsan.spring.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employees employee, BindingResult result) {
		
		if(result.hasErrors()) {
			
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : result.getFieldErrors()) {
				
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		
		Employees newEmployee = employeeService.saveOrUpdateEmployee(employee);
		return new ResponseEntity<Employees>(newEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public Iterable<Employees> getAllEmployee() {
		
		return employeeService.getAllEmployees();
		
	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<?> getById(@PathVariable Long employeeId) {
		
		Employees employee = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<Employees>(employee, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
		
		employeeService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Project Task of id <" + employeeId + "> has been deleted successfully", HttpStatus.OK);
		
	}

}
