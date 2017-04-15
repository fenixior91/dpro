package com.dpro.utils;

import com.dpro.domains.Subject;
import java.util.Map;

public class SubjectUtil {

    public static final String ID = "subjectId";
    public static final String NAME = "subjectName";
    public static final String ECTS = "ects";
    public static final String HOURS = "hours";
    public static final String SUBJECT_TYPE_ID = "subjectTypeId";

    public static Subject generate(Map<String, String> params) {
        Subject subject = new Subject();

        if (params.get(ID) != null) {
            subject.setId(Long.parseLong(params.get(ID)));
        }

        subject.setName(params.get(NAME));
        subject.setEcts(Integer.parseInt(params.get(ECTS)));
        subject.setHours(Integer.parseInt(params.get(HOURS)));

        return subject;
    }
}
