package com.dpro.repositories;

import com.dpro.domains.Instructor;

import java.util.List;

public interface InstructorRepository extends UserRepository{
    
    static final String ID_COLUMN = "instructor_id";
    static final String SCIENCE_DEGREE_COLUMN = "science_degree";
    static final String USER_ID_COLUMN = "user_id";
    static final String ROLE = "ROLE_INSTRUCTOR";

    Instructor findById(Long id);

    List<Instructor> findAll();

    boolean create(Instructor instructor);

    boolean update(Instructor instructor);
}
