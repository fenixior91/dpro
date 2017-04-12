package com.dpro.utils;

import com.dpro.domains.Instructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class InstructorDBUtil {

    public static final String ID_COLUMN = "instructor_id";
    public static final String SCIENCE_DEGREE_COLUMN = "science_degree";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String ROLE = "ROLE_INSTRUCTOR";

    private static final String SQL_FIND_BY_ID_PATTERN
            = "SELECT * FROM user u INNER JOIN instructor i ON u.%s = i.%s WHERE u.%s = ?";
    public static final String SQL_FIND_BY_ID_QUERY
            = String.format(SQL_FIND_BY_ID_PATTERN, USER_ID_COLUMN, USER_ID_COLUMN, USER_ID_COLUMN);

    private static final String SQL_FIND_ALL_PATTERN
            = "SELECT * FROM user u INNER JOIN instructor i ON u.%s = i.%s";
    public static final String SQL_FIND_ALL_QUERY
            = String.format(SQL_FIND_ALL_PATTERN, USER_ID_COLUMN, USER_ID_COLUMN);

    private static final String SQL_INSERT_ROLE_PATTERN
            = "INSERT INTO roles(%s, %s) VALUES(?, '%s')";
    public static final String SQL_INSERT_ROLE_QUERY
            = String.format(SQL_INSERT_ROLE_PATTERN, RolesDBUtil.USERNAME_COLUMN, RolesDBUtil.ROLE_COLUMN, ROLE);

    private static final String SQL_INSERT_PATTERN
            = "INSERT INTO instructor(%s, %s) VALUES(?, (SELECT MAX(%s) FROM user))";
    public static final String SQL_INSERT_QUERY
            = String.format(SQL_INSERT_PATTERN, SCIENCE_DEGREE_COLUMN, USER_ID_COLUMN, USER_ID_COLUMN);

    private static final String SQL_UPDATE_PATTERN
            = "UPDATE instructor SET %s = ? WHERE %s = ?";
    public static final String SQL_UPDATE_QUERY
            = String.format(SQL_UPDATE_PATTERN, SCIENCE_DEGREE_COLUMN, USER_ID_COLUMN);

    private InstructorDBUtil() {

    }

    public static Instructor generate(ResultSet rs) throws SQLException {
        Instructor instructor = new Instructor();

        instructor.setId(rs.getLong(UserDBUtil.ID_COLUMN));
        instructor.setUsername(rs.getString(UserDBUtil.USERNAME_COLUMN));
        instructor.setPassword(rs.getString(UserDBUtil.PASSWORD_COLUMN));
        instructor.setFirstName(rs.getString(UserDBUtil.FIRST_NAME_COLUMN));
        instructor.setLastName(rs.getString(UserDBUtil.LAST_NAME_COLUMN));
        instructor.setEnabled(rs.getBoolean(UserDBUtil.ENABLED_COLUMN));
        instructor.setEmail(rs.getString(UserDBUtil.EMAIL_COLUMN));
        instructor.setDateOfBirth(rs.getDate(UserDBUtil.DATE_OF_BIRTH_COLUMN));
        instructor.setPesel(rs.getString(UserDBUtil.PESEL_COLUMN));

        instructor.setScienceDegree(rs.getString(SCIENCE_DEGREE_COLUMN));

        return instructor;
    }

    public static class InstructorRowMapper implements RowMapper<Instructor> {

        @Override
        public Instructor mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    public static class InstructorResultSetExtractor implements ResultSetExtractor<Instructor> {

        @Override
        public Instructor extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
