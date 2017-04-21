package com.dpro.repositories;

import com.dpro.domains.Instructor;
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
public class InstructorJDBCRepository implements InstructorRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_BY_ID_QUERY
            = "SELECT * FROM user u\n"
            + "INNER JOIN instructor i ON u." + USER_ID_COLUMN + " = i." + USER_ID_COLUMN + "\n"
            + "WHERE u." + USER_ID_COLUMN + " = ?";

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM user u\n"
            + "INNER JOIN instructor i ON u." + USER_ID_COLUMN + " = i." + USER_ID_COLUMN;

    private static final String SQL_INSERT_ROLE_QUERY
            = "INSERT INTO roles(username, role)\n"
            + "VALUES(?, '" + ROLE + "')";

    private static final String SQL_INSERT_INSTRUCTOR_QUERY
            = "INSERT INTO instructor(" + SCIENCE_DEGREE_COLUMN + ", " + USER_ID_COLUMN + ")\n"
            + "VALUES(?, (SELECT MAX(" + USER_ID_COLUMN + ") FROM user))";

    private static final String SQL_UPDATE_INSTRUCTOR_QUERY
            = "UPDATE instructor SET " + SCIENCE_DEGREE_COLUMN + " = ?\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

    public InstructorJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Odnajduje wykładowcę w bazie danych za pomocą identyfikatora
     *
     * @param id ientyfikator wykładowcy
     * @return obiekt wykładowcy pobranego z bazy danych
     */
    @Override
    public Instructor findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new InstructorResultSetExtractor(), id);
    }

    /**
     * Odnajduje wszystkich wykładowców w bazie danych
     *
     * @return lista wykładowców
     */
    @Override
    public List<Instructor> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new InstructorRowMapper());
    }

    /**
     * Tworzy nowego wykładowcę
     *
     * @param instructor obiekt wykładowcy, z którego będą czerpane dane przy
     * zapisie w bazie
     * @return wartość logiczna, czy dodawanie wykładowcy powiodło się
     */
    @Override
    public boolean create(Instructor instructor) {
        template.update(SQL_INSERT_ROLE_QUERY, instructor.getUsername());

        template.update(SQL_INSERT_USER_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                instructor.getEmail(), instructor.getDateOfBirth(), instructor.getPesel());

        template.update(SQL_INSERT_INSTRUCTOR_QUERY, instructor.getScienceDegree());

        return true;
    }

    /**
     * Aktualizuje istniejącego studenta w bazie danych
     *
     * @param instructor obket wykładowcy, z którego będą czerpane dane przy
     * aktualizacji w bazie
     * @return wartość logiczna, czy aktualizowanie wykładowcy powiodło się
     */
    @Override
    public boolean update(Instructor instructor) {
        template.update(SQL_UPDATE_USER_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                instructor.isEnabled(), instructor.getEmail(),
                instructor.getDateOfBirth(), instructor.getPesel(), instructor.getId());

        template.update(SQL_UPDATE_INSTRUCTOR_QUERY,
                instructor.getScienceDegree(),
                instructor.getId());

        return true;
    }

    private static Instructor generate(ResultSet rs) throws SQLException {
        Instructor instructor = new Instructor();

        instructor.setId(rs.getLong(USER_ID_COLUMN));
        instructor.setUsername(rs.getString(USERNAME_COLUMN));
        instructor.setPassword(rs.getString(PASSWORD_COLUMN));
        instructor.setFirstName(rs.getString(FIRST_NAME_COLUMN));
        instructor.setLastName(rs.getString(LAST_NAME_COLUMN));
        instructor.setEnabled(rs.getBoolean(ENABLED_COLUMN));
        instructor.setEmail(rs.getString(EMAIL_COLUMN));
        instructor.setDateOfBirth(rs.getDate(DATE_OF_BIRTH_COLUMN));
        instructor.setPesel(rs.getString(PESEL_COLUMN));

        instructor.setScienceDegree(rs.getString(SCIENCE_DEGREE_COLUMN));

        return instructor;
    }

    private static class InstructorRowMapper implements RowMapper<Instructor> {

        @Override
        public Instructor mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    private static class InstructorResultSetExtractor implements ResultSetExtractor<Instructor> {

        @Override
        public Instructor extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
