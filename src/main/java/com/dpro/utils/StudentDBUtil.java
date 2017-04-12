package com.dpro.utils;

import com.dpro.domains.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class StudentDBUtil {

    public static final String ID_COLUMN = "student_id";
    public static final String ALBUM_COLUMN = "album";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String ROLE = "ROLE_STUDENT";
    
    private static final String SQL_FIND_BY_ID_PATTERN 
            = "SELECT * FROM user u INNER JOIN student s ON u.%s = s.%s WHERE u.%s = ?";
    public static final String SQL_FIND_BY_ID_QUERY
            = String.format(SQL_FIND_BY_ID_PATTERN, USER_ID_COLUMN, USER_ID_COLUMN, USER_ID_COLUMN);
    
    private static final String SQL_FIND_ALL_PATTERN
            = "SELECT * FROM user u INNER JOIN student s ON u.%s = s.%s";
    public static final String SQL_FIND_ALL_QUERY
            = String.format(SQL_FIND_ALL_PATTERN, USER_ID_COLUMN, USER_ID_COLUMN);
    
    private static final String SQL_INSERT_ROLE_PATTERN
            = "INSERT INTO roles(%s, %s) VALUES(?, '%s')";
    public static final String SQL_INSERT_ROLE_QUERY
            = String.format(SQL_INSERT_ROLE_PATTERN, RolesDBUtil.USERNAME_COLUMN, RolesDBUtil.ROLE_COLUMN, ROLE);
    
    private static final String SQL_INSERT_PATTERN
            = "INSERT INTO student(%s, %s) VALUES(?, (SELECT MAX(%s) FROM user))";
    public static final String SQL_INSERT_QUERY
            = String.format(SQL_INSERT_PATTERN, ALBUM_COLUMN, USER_ID_COLUMN, USER_ID_COLUMN);
    
    private static final String SQL_UPDATE_PATTERN
            = "UPDATE student SET %s = ? WHERE %s = ?";
    public static final String SQL_UPDATE_QUERY
            = String.format(SQL_UPDATE_PATTERN, ALBUM_COLUMN, USER_ID_COLUMN);

    private StudentDBUtil() {

    }

    public static Student generate(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong(UserDBUtil.ID_COLUMN));
        student.setUsername(rs.getString(UserDBUtil.USERNAME_COLUMN));
        student.setPassword(rs.getString(UserDBUtil.PASSWORD_COLUMN));
        student.setFirstName(rs.getString(UserDBUtil.FIRST_NAME_COLUMN));
        student.setLastName(rs.getString(UserDBUtil.LAST_NAME_COLUMN));
        student.setEnabled(rs.getBoolean(UserDBUtil.ENABLED_COLUMN));
        student.setEmail(rs.getString(UserDBUtil.EMAIL_COLUMN));
        student.setDateOfBirth(rs.getDate(UserDBUtil.DATE_OF_BIRTH_COLUMN));
        student.setPesel(rs.getString(UserDBUtil.PESEL_COLUMN));

        student.setAlbum(rs.getString(ALBUM_COLUMN));

        return student;
    }

    public static class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    public static class StudentResultSetExtractor implements ResultSetExtractor<Student> {

        @Override
        public Student extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
