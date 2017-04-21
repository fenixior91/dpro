package com.dpro.repositories;

import com.dpro.domains.SubjectType;
import java.util.List;

public interface SubjectTypeRepository {

    static final String SUBJECT_TYPE_ID_COLUMN = "subject_type_id";
    static final String SUBJECT_TYPE_NAME_COLUMN = "subject_type_name";

    SubjectType findById(Long id);

    List<SubjectType> findAll();

    boolean create(SubjectType subjectType);
}
