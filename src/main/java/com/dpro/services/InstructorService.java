package com.dpro.services;

import java.util.List;
import java.util.Map;

import com.dpro.domains.Instructor;

public interface InstructorService {
	Instructor findById(Long id);
	List<Instructor> findAll();
	boolean addInstructor(Map<String, String> params);
}
