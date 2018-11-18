package com.rajasthani.timetracking.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajasthani.timetracking.model.Employee;
import com.rajasthani.timetracking.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees")
	public List<Employee> retrieveAll() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public Employee retrieve(@PathVariable long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);

		if (!employeeOptional.isPresent())
			throw new IllegalArgumentException("Employee with id " + id + " not found.");

		return employeeOptional.get();
	}

	@DeleteMapping("/employees/{id}")
	public void delete(@PathVariable Long id) {
		employeeRepository.deleteById(id);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> create(@RequestBody Employee employee) {
		Employee saved = employeeRepository.save(employee);
		logger.info("Created Employee {}", saved);

		return ResponseEntity.ok(saved);

	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Employee employee, @PathVariable Long id) {

		Optional<Employee> employeeOptional = employeeRepository.findById(id);

		if (!employeeOptional.isPresent())
			return ResponseEntity.notFound().build();

		employee.setId(id);
		employeeRepository.save(employee);
		logger.info("Updated Employee {}", employee);
		return ResponseEntity.ok(employee);
	}

}
