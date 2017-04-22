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

import static com.dpro.utils.DatabaseColumns.*;

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
            = "INSERT INTO roles(" + USER_NAME_COLUMN + ", " + ROLES_ROLE_COLUMN + ")\n"
            + "VALUES(?, '" + STUDENT_ROLE + "')";

    private static final String SQL_INSERT_STUDENT_QUERY
            = "INSERT INTO student(" + ALBUM_COLUMN + ", " + USER_ID_COLUMN + ")\n"
            + "VALUES(?, (SELECT MAX(" + USER_ID_COLUMN + ") FROM user))";

    private static final String SQL_UPDATE_STUDENT_QUERY
            = "UPDATE student SET " + ALBUM_COLUMN + " = ?\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

    private static final String SQL_INSERT_USER_QUERY
            = "INSERT INTO user(" + USER_NAME_COLUMN + ", " + USER_PASSWORD_COLUMN + ", " + USER_FIRST_NAME_COLUMN + ", " + USER_LAST_NAME_COLUMN + ", " + USER_ENABLED_COLUMN + ", " + USER_EMAIL_COLUMN + ", " + USER_DATE_OF_BIRTH_COLUMN + ", " + USER_PESEL_COLUMN + ")\n"
            + "VALUES(?, ?, ?, ?, true, ?, ?, ?)";

    private static final String SQL_UPDATE_USER_QUERY
            = "UPDATE user SET " + USER_NAME_COLUMN + " = ?, " + USER_PASSWORD_COLUMN + " = ?,\n"
            + USER_FIRST_NAME_COLUMN + " = ?, " + USER_LAST_NAME_COLUMN + " = ?,\n"
            + USER_ENABLED_COLUMN + " = ?," + USER_EMAIL_COLUMN + " = ?,\n"
            + USER_DATE_OF_BIRTH_COLUMN + " = ?, " + USER_PESEL_COLUMN + " = ?\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

    public StudentJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Odnajduje studenta w bazie danych za pomocą id
     *
     * @param id ientyfikator studenta
     * @return obiekt studenta pobranego z bazy danych
     */
    @Override
    public Student findById(Long id) {
        Student student = template.query(SQL_FIND_BY_ID_QUERY, new StudentResultSetExtractor(), id);
        return student;
    }

    /**
     * Odnajduje wszystkich studentów w bazie danych
     *
     * @return lista studentów
     */
    @Override
    public List<Student> findAll() {
        List<Student> students = template.query(SQL_FIND_ALL_QUERY, new StudentRowMapper());
        return students;
    }

    /**
     * Tworzy nowego studenta
     *
     * @param student obiekt studenta, z którego będą czerpane dane przy zapisie
     * w bazie
     * @return wartość logiczna, czy dodawanie studenta powiodło się
     */
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

    /**
     * Aktualizuje istniejącego studenta w bazie danych
     *
     * @param student obket studenta, z którego będą czerpane dane przy
     * aktualizacji w bazie
     * @return wartość logiczna, czy aktualizowanie studenta powiodło się
     */
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
        student.setUsername(rs.getString(USER_NAME_COLUMN));
        student.setPassword(rs.getString(USER_PASSWORD_COLUMN));
        student.setFirstName(rs.getString(USER_FIRST_NAME_COLUMN));
        student.setLastName(rs.getString(USER_LAST_NAME_COLUMN));
        student.setEnabled(rs.getBoolean(USER_ENABLED_COLUMN));
        student.setEmail(rs.getString(USER_EMAIL_COLUMN));
        student.setDateOfBirth(rs.getDate(USER_DATE_OF_BIRTH_COLUMN));
        student.setPesel(rs.getString(USER_PESEL_COLUMN));

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
