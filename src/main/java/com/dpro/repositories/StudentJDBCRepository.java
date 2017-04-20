package com.dpro.repositories;

import com.dpro.domains.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class StudentJDBCRepository implements StudentRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_BY_ID_QUERY
            = "SELECT * FROM user u\n"
            + "INNER JOIN student s ON u." + USER_ID_COLUMN + " = s." + USER_ID_COLUMN + "\n"
            + "WHERE u." + USER_ID_COLUMN + " = ?";

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM user u\n"
            + "INNER JOIN student s ON u." + USER_ID_COLUMN + " = s." + USER_ID_COLUMN;

    private static final String SQL_INSERT_ROLE_QUERY
            = "INSERT INTO roles(username, role)\n"
            + "VALUES(?, '" + ROLE + "')";

    private static final String SQL_INSERT_STUDENT_QUERY
            = "INSERT INTO student(" + ALBUM_COLUMN + ", " + USER_ID_COLUMN + ")\n"
            + "VALUES(?, (SELECT MAX(" + USER_ID_COLUMN + ") FROM user))";

    private static final String SQL_UPDATE_STUDENT_QUERY
            = "UPDATE student SET " + ALBUM_COLUMN + " = ?\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

    public StudentJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Student findById(Long id) {
        Student student = template.query(SQL_FIND_BY_ID_QUERY, new StudentResultSetExtractor(), id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = template.query(SQL_FIND_ALL_QUERY, new StudentRowMapper());
        return students;
    }

    @Override
    public boolean create(Student student) {
        template.update(SQL_INSERT_ROLE_QUERY, student.getUsername());

        template.update(SQL_INSERT_USER_QUERY,
                student.getUsername(), student.getPassword(),
                student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getDateOfBirth(), student.getPesel());

        template.update(SQL_INSERT_STUDENT_QUERY, student.getAlbum());

        return true;
    }

    @Override
    public boolean update(Student student) {
        System.out.println("UPDATE STUDENT");
        System.out.println(student);
        template.update(SQL_UPDATE_USER_QUERY,
                student.getUsername(), student.getPassword(),
                student.getFirstName(), student.getLastName(),
                student.isEnabled(), student.getEmail(),
                student.getDateOfBirth(), student.getPesel(), student.getId());

        template.update(SQL_UPDATE_STUDENT_QUERY, student.getAlbum(), student.getId());

        return true;
    }

    private static Student generate(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getLong(USER_ID_COLUMN));
        student.setUsername(rs.getString(USERNAME_COLUMN));
        student.setPassword(rs.getString(PASSWORD_COLUMN));
        student.setFirstName(rs.getString(FIRST_NAME_COLUMN));
        student.setLastName(rs.getString(LAST_NAME_COLUMN));
        student.setEnabled(rs.getBoolean(ENABLED_COLUMN));
        student.setEmail(rs.getString(EMAIL_COLUMN));
        student.setDateOfBirth(rs.getDate(DATE_OF_BIRTH_COLUMN));
        student.setPesel(rs.getString(PESEL_COLUMN));

        student.setAlbum(rs.getString(ALBUM_COLUMN));

        return student;
    }

    private static class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    private static class StudentResultSetExtractor implements ResultSetExtractor<Student> {

        @Override
        public Student extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
