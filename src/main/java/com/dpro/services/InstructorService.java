package com.dpro.services;

import java.util.List;

import com.dpro.domains.Instructor;
import com.dpro.domains.Subject;
import java.util.Map;

public interface InstructorService {

    Instructor findById(Long id);

    List<Instructor> findAll();

    boolean attach(Subject subject);

    boolean attach(List<Subject> subjects);

    boolean create(Map<String, String> params);

    boolean update(Map<String, String> params);
}
