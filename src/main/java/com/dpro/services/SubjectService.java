package com.dpro.services;

import com.dpro.domains.Subject;
import com.dpro.domains.User;
import java.util.List;
import java.util.Map;

public interface SubjectService {
    List<Subject> findAll();
    Subject findById(Long id);
    boolean create(Map<String, String> params);
    boolean update(Map<String, String> params);
}
