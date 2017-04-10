package com.dpro.utils;

import com.dpro.domains.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class StudentDBUtil {

    public static final String ALBUM_COLUMN = "album";

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
        student.setEmail(rs.getString(UserDBUtil.ENABLED_COLUMN));
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
