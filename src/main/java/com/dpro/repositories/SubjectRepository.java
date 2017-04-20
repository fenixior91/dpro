package com.dpro.repositories;

import com.dpro.domains.Subject;
import java.util.List;

public interface SubjectRepository {

    List<Subject> findAll();

    List<Subject> findAllInUser(Long id);

    List<Subject> findAllNotInUser(Long id);

    boolean attachToUser(Long id, Subject subject);

    boolean attachToUser(Long id, List<Subject> subjects);

    Subject findById(Long id);

    boolean create(Subject subject);

    boolean update(Subject subject);
}
