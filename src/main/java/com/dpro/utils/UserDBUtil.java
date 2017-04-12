package com.dpro.utils;

public class UserDBUtil {

    public static final String ID_COLUMN = "user_id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String ENABLED_COLUMN = "enabled";
    public static final String EMAIL_COLUMN = "email";
    public static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
    public static final String PESEL_COLUMN = "pesel";

    private static final String SQL_INSERT_PATTERN
            = "INSERT INTO user(%s, %s, %s, %s, %s, %s, %s, %s) VALUES(?, ?, ?, ?, true, ?, ?, ?)";
    public static final String SQL_INSERT_QUERY
            = String.format(SQL_INSERT_PATTERN, USERNAME_COLUMN, PASSWORD_COLUMN, FIRST_NAME_COLUMN, LAST_NAME_COLUMN, ENABLED_COLUMN, EMAIL_COLUMN, DATE_OF_BIRTH_COLUMN, PESEL_COLUMN);

    private static final String SQL_UPDATE_PATTERN
            = "UPDATE user SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?";
    public static final String SQL_UPDATE_QUERY
            = String.format(SQL_UPDATE_PATTERN, USERNAME_COLUMN, PASSWORD_COLUMN, FIRST_NAME_COLUMN, LAST_NAME_COLUMN, ENABLED_COLUMN, EMAIL_COLUMN, DATE_OF_BIRTH_COLUMN, PESEL_COLUMN, ID_COLUMN);

    private UserDBUtil() {

    }
}
