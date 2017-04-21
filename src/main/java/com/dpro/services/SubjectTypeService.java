package com.dpro.services;

import com.dpro.domains.SubjectType;
import java.util.List;
import java.util.Map;

public interface SubjectTypeService {

    SubjectType findById(Long id);

    List<SubjectType> findAll();

    boolean create(Map<String, String> params);
}
