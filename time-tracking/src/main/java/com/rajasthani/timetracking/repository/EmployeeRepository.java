package com.rajasthani.timetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajasthani.timetracking.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
