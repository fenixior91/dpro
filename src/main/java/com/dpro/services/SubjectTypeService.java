package com.dpro.services;

import com.dpro.domains.SubjectType;
import java.util.List;

public interface SubjectTypeService {

    SubjectType findById(Long id);

    List<SubjectType> findAll();
}
