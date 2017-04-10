package com.dpro.repositories;

import com.dpro.domains.SubjectType;
import java.util.List;

public interface SubjectTypeRepository {

    List<SubjectType> findAll();

    SubjectType findById(Long id);

    boolean create(SubjectType subjectType);
}
