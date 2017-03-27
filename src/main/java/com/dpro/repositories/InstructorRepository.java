package com.dpro.repositories;

import com.dpro.domains.Instructor;
import com.dpro.domains.Student;

import java.util.Date;
import java.util.List;

public interface InstructorRepository {
	Instructor findById(Long id);

	Instructor findByPesel(String pesel);

	Instructor findByEmail(String email);

	List<Instructor> findAll();

	List<Instructor> findAllByFirstName(String firstName);

	List<Instructor> findAllByLastName(String lastName);

	List<Instructor> findAllByDateOfBirth(Date dateOfBirth);

	List<Instructor> findAllByEnabled(boolean enabled);

	List<Instructor> findByScienceDegree(String scienceDegree);
}
