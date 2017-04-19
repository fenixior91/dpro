package com.dpro.repositories;

import com.dpro.domains.Instructor;
import com.dpro.domains.Subject;

import java.util.List;

public interface InstructorRepository {
	Instructor findById(Long id);
	List<Instructor> findAll();
        boolean attach(Subject subject);
        boolean attach(List<Subject> subjects);
	boolean create(Instructor instructor);
	boolean update(Instructor instructor);
}
