package com.rajasthani.timetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajasthani.timetracking.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
