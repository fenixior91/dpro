package com.dpro.utils;

import com.dpro.domains.SubjectType;
import java.util.Map;

public class SubjectTypeUtil {

    public static final String ID = "subject_type_id";
    public static final String NAME = "subject_type_name";
    
    private SubjectTypeUtil() {
        
    }
    
    public SubjectType generate(Map<String, String> params) {
        SubjectType subjectType = new SubjectType();
        subjectType.setId(Long.parseLong(params.get(ID)));
        subjectType.setName(params.get(NAME));
        
        return subjectType;
    }
}
