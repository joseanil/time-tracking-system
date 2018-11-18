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

import com.rajasthani.timetracking.model.Institution;
import com.rajasthani.timetracking.repository.InstitutionRepository;

@RestController
public class InstitutionController {

	Logger logger = LoggerFactory.getLogger(InstitutionController.class);

	@Autowired
	private InstitutionRepository repository;

	@GetMapping("/institutions")
	public List<Institution> retrieveAll() {
		return repository.findAll();
	}

	@GetMapping("/institutions/{id}")
	public Institution retrieve(@PathVariable long id) {
		Optional<Institution> optional = repository.findById(id);

		if (!optional.isPresent())
			throw new IllegalArgumentException("Institution with id " + id + " not found.");

		return optional.get();
	}

	@DeleteMapping("/institutions/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PostMapping("/institutions")
	public ResponseEntity<Object> create(@RequestBody Institution entity) {
		Institution saved = repository.save(entity);
		logger.info("Created Employee {}", saved);

		return ResponseEntity.ok(saved);

	}

	@PutMapping("/institutions/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Institution entity, @PathVariable Long id) {

		Optional<Institution> optional = repository.findById(id);

		if (!optional.isPresent())
			return ResponseEntity.notFound().build();

		entity.setId(id);
		repository.save(entity);
		logger.info("Updated Entity {}", entity);
		return ResponseEntity.ok(entity);
	}

}
