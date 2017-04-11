package com.dpro.utils;

import com.dpro.domains.Instructor;
import java.sql.Date;
import java.util.Map;

public class InstructorUtil {

    public static final String ID = "id";
    public static final String SCIENCE_DEGREE = "scienceDegree";

    private InstructorUtil() {

    }

    public static Instructor generate(Map<String, String> params) {
        Instructor instructor = new Instructor();
        
        if (params.get(ID) != null) {
            instructor.setId(Long.parseLong(params.get(ID)));
        }
        
        instructor.setUsername(params.get(UserUtil.USERNAME));
        instructor.setPassword(params.get(UserUtil.PASSWORD));
        instructor.setFirstName(params.get(UserUtil.FIRST_NAME));
        instructor.setLastName(params.get(UserUtil.LAST_NAME));
        instructor.setEnabled(Boolean.parseBoolean(params.get(UserUtil.ENABLED)));
        instructor.setEmail(params.get(UserUtil.EMAIL));
        instructor.setDateOfBirth(Date.valueOf(params.get(UserUtil.DATE_OF_BIRTH)));
        instructor.setPesel(params.get(UserUtil.PESEL));

        instructor.setScienceDegree(params.get(SCIENCE_DEGREE));

        return instructor;
    }
}
