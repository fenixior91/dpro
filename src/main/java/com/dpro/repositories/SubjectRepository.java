package com.dpro.repositories;

import com.dpro.domains.Subject;
import com.dpro.domains.User;
import java.util.List;

public interface SubjectRepository {
    List<Subject> findAll();
    List<Subject> findAllByUser(User user); 
    Subject findById(Long id);
    boolean create(Subject subject);
}
