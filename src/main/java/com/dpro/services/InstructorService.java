package com.dpro.services;

import java.util.List;

import com.dpro.domains.Instructor;
import java.util.Map;

public interface InstructorService {
	Instructor findById(Long id);
	List<Instructor> findAll();
	boolean create(Map<String, String> params);
	boolean update(Map<String, String> instructor);
}
