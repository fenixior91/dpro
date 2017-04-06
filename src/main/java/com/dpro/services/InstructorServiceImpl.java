package com.dpro.services;

import java.util.List;

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
}
