package com.dpro.utils;

import com.dpro.domains.Instructor;
import java.sql.Date;
import java.util.Map;

public class InstructorUtil {

    public static final String SCIENCE_DEGREE = "scienceDegree";

    private InstructorUtil() {

    }

    public static Instructor generate(Map<String, String> params) {
        Instructor student = new Instructor();
        student.setUsername(params.get(UserUtil.USERNAME));
        student.setPassword(params.get(UserUtil.PASSWORD));
        student.setFirstName(params.get(UserUtil.FIRST_NAME));
        student.setLastName(params.get(UserUtil.LAST_NAME));
        student.setEnabled(Boolean.parseBoolean(params.get(UserUtil.ENABLED)));
        student.setEmail(params.get(UserUtil.EMAIL));
        student.setDateOfBirth(Date.valueOf(params.get(UserUtil.DATE_OF_BIRTH)));
        student.setPesel(params.get(UserUtil.PESEL));

        student.setScienceDegree(SCIENCE_DEGREE);

        return student;
    }
}
