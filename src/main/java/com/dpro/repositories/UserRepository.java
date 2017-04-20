package com.dpro.repositories;

public interface UserRepository {

    static final String USER_ID_COLUMN = "user_id";
    static final String USERNAME_COLUMN = "username";
    static final String PASSWORD_COLUMN = "password";
    static final String FIRST_NAME_COLUMN = "first_name";
    static final String LAST_NAME_COLUMN = "last_name";
    static final String ENABLED_COLUMN = "enabled";
    static final String EMAIL_COLUMN = "email";
    static final String DATE_OF_BIRTH_COLUMN = "date_of_birth";
    static final String PESEL_COLUMN = "pesel";

    static final String SQL_INSERT_USER_QUERY
            = "INSERT INTO user(" + USERNAME_COLUMN + ", " + PASSWORD_COLUMN + ", " + FIRST_NAME_COLUMN + ", " + LAST_NAME_COLUMN + ", " + ENABLED_COLUMN + ", " + EMAIL_COLUMN + ", " + DATE_OF_BIRTH_COLUMN + ", " + PESEL_COLUMN + "\n"
            + "VALUES(?, ?, ?, ?, true, ?, ?, ?)";

    static final String SQL_UPDATE_USER_QUERY
            = "UPDATE user SET " + USERNAME_COLUMN + " = ?, " + PASSWORD_COLUMN + " = ?,\n"
            + FIRST_NAME_COLUMN + " = ?, " + LAST_NAME_COLUMN + " = ?,\n"
            + ENABLED_COLUMN + " = ?," + EMAIL_COLUMN + " = ?,\n"
            + DATE_OF_BIRTH_COLUMN + " = ?, " + PESEL_COLUMN + " = ?\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

}
