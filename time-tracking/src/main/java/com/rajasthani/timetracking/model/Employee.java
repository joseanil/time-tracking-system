package com.rajasthani.timetracking.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 5913349930835183136L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String title;
	private String middleName;
	private String email;
	private String phone;
	
	@OneToOne
	private Address address;

	@ManyToOne
	private Institution institution;
	
	@ManyToOne
	private Department department;

	@ManyToOne
	private Employee manager;
	
	@ManyToMany
	private Set<Project> projects;
	

}
