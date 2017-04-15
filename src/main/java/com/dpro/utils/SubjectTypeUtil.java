package com.dpro.utils;

import com.dpro.domains.SubjectType;
import java.util.Map;

public class SubjectTypeUtil {

    public static final String ID = "subjectTypeId";
    public static final String NAME = "subjectTypeName";

    private SubjectTypeUtil() {

    }

    public static SubjectType generate(Map<String, String> params) {
        SubjectType subjectType = new SubjectType();

        if (params.get(ID) != null) {
            subjectType.setId(Long.parseLong(params.get(ID)));
        }
        
        subjectType.setName(params.get(NAME));

        return subjectType;
    }
}
