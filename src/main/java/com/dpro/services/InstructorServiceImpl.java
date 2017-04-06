package com.dpro.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpro.domains.Instructor;
import com.dpro.repositories.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Override
	public Instructor findById(Long id) {
		return instructorRepository.findById(id);
	}
	
	@Override
	public List<Instructor> findAll() {
		return instructorRepository.findAll();
	}
	
	@Override
	public boolean addInstructor(Map<String, String> params) {
		Instructor instructor = new Instructor();
		instructor.setUsername(params.get("username"));
		instructor.setPassword(params.get("password"));
		instructor.setFirstName(params.get("first_name"));
		instructor.setLastName(params.get("last_name"));
		instructor.setEnabled(true);
		instructor.setEmail(params.get("email"));
		instructor.setScienceDegree(params.get("science_degree"));
		
		return instructorRepository.addInstructor(instructor);
	}
}
