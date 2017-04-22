package com.dpro.repositories;

import com.dpro.domains.SubjectType;
import java.util.List;

public interface SubjectTypeRepository {

    SubjectType findById(Long id);

    List<SubjectType> findAll();
}
