package com.dpro.repositories;

import com.dpro.domains.Subject;
import java.util.List;

public interface SubjectRepository {

    Subject findById(Long id);

    List<Subject> findAll();

    List<Subject> findAllInUser(Long id);

    List<Subject> findAllNotInUser(Long id);

    boolean create(Subject subject);

    boolean update(Subject subject);

    boolean attachToUser(Long id, Subject subject);

    boolean attachToUser(Long id, List<Subject> subjects);

}
