package com.dpro.services;

import com.dpro.domains.SubjectType;
import java.util.List;
import java.util.Map;

public interface SubjectTypeService {
    List<SubjectType> findAll();
    SubjectType findById(Long id);
    boolean create(SubjectType subjectType);
    boolean create(Map<String, String> params);
}
