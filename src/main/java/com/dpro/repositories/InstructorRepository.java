package com.dpro.repositories;

import com.dpro.domains.Instructor;

import java.util.List;

public interface InstructorRepository {
	Instructor findById(Long id);
	List<Instructor> findAll();

}
