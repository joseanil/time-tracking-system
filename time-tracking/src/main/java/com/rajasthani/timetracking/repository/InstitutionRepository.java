package com.rajasthani.timetracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajasthani.timetracking.model.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

}
