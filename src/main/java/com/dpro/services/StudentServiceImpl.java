package com.dpro.services;

import com.dpro.domains.Student;
import com.dpro.repositories.StudentRepository;
import com.dpro.utils.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public boolean create(Map<String, String> params) {
        Student student = StudentUtil.generate(params);
        
        return studentRepository.create(student);
    }
}
