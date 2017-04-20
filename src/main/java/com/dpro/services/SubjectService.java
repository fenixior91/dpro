package com.dpro.services;

import com.dpro.domains.Subject;
import java.util.List;
import java.util.Map;

public interface SubjectService {

    List<Subject> findAll();

    List<Subject> findAllInUser(Long id);

    List<Subject> findAllNotInUser(Long id);

    boolean attachToUser(Long id, Subject subject);

    boolean attachToUser(Long id, List<Subject> subjects);

    Subject findById(Long id);

    boolean create(Map<String, String> params);

    boolean update(Map<String, String> params);
}
