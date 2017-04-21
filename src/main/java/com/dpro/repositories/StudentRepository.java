package com.dpro.repositories;

import com.dpro.domains.Student;

import java.util.List;

public interface StudentRepository extends UserRepository {

    public static final String ID_COLUMN = "student_id";
    public static final String ALBUM_COLUMN = "album";
    public static final String ROLE = "ROLE_STUDENT";

    Student findById(Long id);

    List<Student> findAll();

    boolean create(Student student);

    boolean update(Student student);
}
