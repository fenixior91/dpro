package com.dpro.repositories;

import com.dpro.domains.Subject;
import java.util.List;

public interface SubjectRepository extends UserRepository{

    static final String SUBJECT_ID_COLUMN = "subject_id";
    static final String NAME_COLUMN = "subject_name";
    static final String ECTS_COLUMN = "ects";
    static final String HOURS_COLUMN = "hours";
    static final String SUBJECT_TYPE_ID_COLUMN = "subject_type_id";
    static final String SUBJECT_TYPE_NAME_COLUMN = "subject_type_name";
    static final String USER_ID = "user_id";

    List<Subject> findAll();

    List<Subject> findAllInUser(Long id);

    List<Subject> findAllNotInUser(Long id);

    boolean attachToUser(Long id, Subject subject);

    boolean attachToUser(Long id, List<Subject> subjects);

    Subject findById(Long id);

    boolean create(Subject subject);

    boolean update(Subject subject);
}
