package com.dpro.services;

import com.dpro.domains.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    Student findById(Long id);

    List<Student> findAll();

    boolean create(Map<String, String> params);

    boolean update(Map<String, String> params);
}
