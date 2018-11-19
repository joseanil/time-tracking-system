package com.rajasthani.timetracking.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajasthani.timetracking.model.Department;
import com.rajasthani.timetracking.repository.DepartmentRepository;

@RestController
@CrossOrigin(maxAge = 3600)
public class DepartmentController {

	Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentRepository repository;

	@GetMapping("/departments")
	public List<Department> retrieveAll() {
		return repository.findAll();
	}

	@GetMapping("/departments/{id}")
	public Department retrieve(@PathVariable long id) {
		Optional<Department> optional = repository.findById(id);

		if (!optional.isPresent())
			throw new IllegalArgumentException("Department with id " + id + " not found.");

		return optional.get();
	}

	@DeleteMapping("/departments/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PostMapping("/departments")
	public ResponseEntity<Object> create(@RequestBody Department entity) {
		Department saved = repository.save(entity);
		logger.info("Created Department {}", saved);

		return ResponseEntity.ok(saved);

	}

	@PutMapping("/departments/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Department entity, @PathVariable Long id) {

		Optional<Department> optional = repository.findById(id);

		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		entity.setId(id);
		repository.save(entity);
		logger.info("Updated Department {}", entity);
		return ResponseEntity.ok(entity);
	}

}
