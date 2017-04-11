package com.dpro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpro.domains.Instructor;
import com.dpro.repositories.InstructorRepository;
import com.dpro.utils.InstructorUtil;
import java.util.Map;

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
    public boolean create(Map<String, String> params) {
        Instructor instructor = InstructorUtil.generate(params);
        
        return instructorRepository.create(instructor);
    }

    @Override
    public boolean update(Map<String, String> params) {
        Instructor instructor = InstructorUtil.generate(params);
        
        return instructorRepository.update(instructor);
    }
}
