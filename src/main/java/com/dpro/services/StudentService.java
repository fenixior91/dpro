package com.dpro.services;

import com.dpro.domains.Student;

import java.util.List;

public interface StudentService {

    Student findById(Long id);

    List<Student> findAll();

    boolean create(Student student);

    boolean update(Student student);
}
