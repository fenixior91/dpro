package com.dpro.utils;

import com.dpro.domains.Subject;
import java.util.Map;

public class SubjectUtil {
    
    public static final String ID = "subject_id";
    public static final String NAME = "subject_name";
    public static final String ECTS = "ects";
    public static final String HOURS = "hours";
    
    public Subject generate(Map<String, String> params) {
        Subject subject = new Subject();
        subject.setId(Long.parseLong(params.get(ID)));
        subject.setName(params.get(NAME));
        subject.setEcts(Integer.parseInt(params.get(ECTS)));
        subject.setHours(Integer.parseInt(params.get(HOURS)));
        
        return subject;
    }
}
