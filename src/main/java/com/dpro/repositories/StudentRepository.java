package com.dpro.repositories;

import com.dpro.domains.Student;

import java.util.List;

public interface StudentRepository {

    Student findById(Long id);

    List<Student> findAll();

    boolean create(Student student);

    boolean update(Student student);
}
