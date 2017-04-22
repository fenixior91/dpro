package com.dpro.utils;

/**
 * Wszytkie kolumny zanjdujące się w bazie danych. Przydatne do refaktoryzacji
 * bazy danych.
 *
 * @author Tomasz Truszkowski
 */
public class DatabaseColumns {

    public static final String USER_ID_COLUMN = "user_id";
    public static final String USER_NAME_COLUMN = "user_name";
    public static final String USER_PASSWORD_COLUMN = "user_password";
    public static final String USER_FIRST_NAME_COLUMN = "user_first_name";
    public static final String USER_LAST_NAME_COLUMN = "user_last_name";
    public static final String USER_ENABLED_COLUMN = "user_enabled";
    public static final String USER_EMAIL_COLUMN = "user_email";
    public static final String USER_DATE_OF_BIRTH_COLUMN = "user_date_of_birth";
    public static final String USER_PESEL_COLUMN = "user_pesel";
   
    public static final String ROLES_ROLE_COLUMN = "role";
    
    public static final String STUDENT_ID_COLUMN = "student_id";
    public static final String ALBUM_COLUMN = "student_album";
    public static final String STUDENT_ROLE = "ROLE_STUDENT";

    public static final String INSTRUCTOR_ID_COLUMN = "instructor_id";
    public static final String SCIENCE_DEGREE_COLUMN = "instructor_science_degree";
    public static final String INSTRUCTOR_ROLE = "ROLE_INSTRUCTOR";

    public static final String SUBJECT_ID_COLUMN = "subject_id";
    public static final String SUBJECT_NAME_COLUMN = "subject_name";
    public static final String SUBJECT_ECTS_COLUMN = "subject_ects";
    public static final String SUBJECT_HOURS_COLUMN = "subject_hours";

    public static final String SUBJECT_TYPE_ID_COLUMN = "subject_type_id";
    public static final String SUBJECT_TYPE_NAME_COLUMN = "subject_type_name";

    public static final String MARK_ID_COLUMN = "mark_id";
    public static final String MARK_VALUE_COLUMN = "mark_value";
    public static final String MARK_DESC_COLUMN = "mark_desc";
    public static final String MARK_IS_PASS_COLUMN = "mark_is_pass";
}
