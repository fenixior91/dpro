package com.dpro.services;

import java.util.List;

import com.dpro.domains.Instructor;

public interface InstructorService {

    Instructor findById(Long id);

    List<Instructor> findAll();

    boolean create(Instructor instructor);

    boolean update(Instructor instructor);
}
