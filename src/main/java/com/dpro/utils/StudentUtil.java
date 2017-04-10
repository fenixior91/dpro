package com.dpro.utils;

import com.dpro.domains.Student;
import java.sql.Date;
import java.util.Map;

public class StudentUtil {
    
    public static final String ALBUM = "album";
    
    private StudentUtil() {
        
    }
    
    public static Student generate(Map<String, String> params) {
        Student student = new Student();
        student.setUsername(params.get(UserUtil.USERNAME));
        student.setPassword(params.get(UserUtil.PASSWORD));
        student.setFirstName(params.get(UserUtil.FIRST_NAME));
        student.setLastName(params.get(UserUtil.LAST_NAME));
        student.setEnabled(Boolean.parseBoolean(params.get(UserUtil.ENABLED)));
        student.setEmail(params.get(UserUtil.EMAIL));
        student.setDateOfBirth(Date.valueOf(params.get(UserUtil.DATE_OF_BIRTH)));
        student.setPesel(params.get(UserUtil.PESEL));
        
        student.setAlbum(ALBUM);
        
        return student;
    }
}
