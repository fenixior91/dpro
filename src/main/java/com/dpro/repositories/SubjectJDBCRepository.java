package com.dpro.repositories;

import com.dpro.domains.Subject;
import com.dpro.domains.SubjectType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.dpro.utils.DatabaseColumns.*;
import static com.dpro.utils.DatabaseTables.*;

@Repository
public class SubjectJDBCRepository implements SubjectRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM " + SUBJECTS_TABLE + " s\n"
            + "INNER JOIN " + SUBJECT_TYPES_TABLE + " st ON s." + SUBJECT_TYPE_ID_COLUMN + " = st." + SUBJECT_TYPE_ID_COLUMN;

    private static final String SQL_FIND_BY_ID_QUERY
            = "SELECT * FROM " + SUBJECTS_TABLE + " s\n"
            + "INNER JOIN " + SUBJECT_TYPES_TABLE + " st ON s." + SUBJECT_TYPE_ID_COLUMN + " = st." + SUBJECT_TYPE_ID_COLUMN + "\n"
            + " WHERE s." + SUBJECT_TYPE_ID_COLUMN + " = ?";

    private static final String SQL_INSERT_QUERY
            = "INSERT INTO " + SUBJECTS_TABLE + "(" + SUBJECT_NAME_COLUMN + ", " + SUBJECT_ECTS_COLUMN + ", " + SUBJECT_HOURS_COLUMN + ", " + SUBJECT_TYPE_ID_COLUMN + ")\n"
            + "VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE_QUERY
            = "UPDATE " + SUBJECTS_TABLE + "\n"
            + "SET " + SUBJECT_NAME_COLUMN + " = ?, " + SUBJECT_ECTS_COLUMN + " = ?, " + SUBJECT_HOURS_COLUMN + " = ?, " + SUBJECT_TYPE_ID_COLUMN + " = ?\n"
            + "WHERE " + SUBJECT_ID_COLUMN + " = ?\n";

    private static final String SQL_FIND_ALL_IN_USER_QUERY
            = "SELECT s." + SUBJECT_ID_COLUMN + ", s." + SUBJECT_NAME_COLUMN + ", s." + SUBJECT_ECTS_COLUMN + ", s." + SUBJECT_HOURS_COLUMN + ", st." + SUBJECT_TYPE_ID_COLUMN + ", st." + SUBJECT_TYPE_NAME_COLUMN + "\n"
            + "FROM " + USERS_TABLE + " u\n"
            + "INNER JOIN " + USERS_SUBJECTS_TABLE + " us ON u." + USER_ID_COLUMN + " = us." + USER_ID_COLUMN + " AND us." + USER_ID_COLUMN + " = ?\n"
            + "INNER JOIN " + SUBJECTS_TABLE + " s ON s." + SUBJECT_ID_COLUMN + " = us." + SUBJECT_ID_COLUMN + "\n"
            + "INNER JOIN " + SUBJECT_TYPES_TABLE + " st ON st." + SUBJECT_TYPE_ID_COLUMN + " = s." + SUBJECT_TYPE_ID_COLUMN;

    private static final String SQL_FIND_ALL_NOT_IN_USER_QUERY
            = "SELECT s." + SUBJECT_ID_COLUMN + ", s." + SUBJECT_NAME_COLUMN + ", s." + SUBJECT_ECTS_COLUMN + ", s." + SUBJECT_HOURS_COLUMN + ", st." + SUBJECT_TYPE_ID_COLUMN + ", st. " + SUBJECT_TYPE_NAME_COLUMN + "\n"
            + "FROM " + SUBJECTS_TABLE + " s\n"
            + "INNER JOIN " + SUBJECT_TYPES_TABLE + " st ON st." + SUBJECT_TYPE_ID_COLUMN + " = s." + SUBJECT_TYPE_ID_COLUMN + "\n"
            + "WHERE s." + SUBJECT_ID_COLUMN + " NOT IN\n"
            + "(SELECT us." + SUBJECT_ID_COLUMN + " FROM " + USERS_SUBJECTS_TABLE + " us WHERE us." + USER_ID_COLUMN + " = ?)";

    private static final String SQL_DETACH_SUBJECTS_IN_USER_QUERY
            = "DELETE FROM " + USERS_SUBJECTS_TABLE + "\n"
            + "WHERE " + USER_ID_COLUMN + " = ?";

    private static final String SQL_ATTACH_SUBJECT_IN_USER_QUERY
            = "INSERT INTO " + USERS_SUBJECTS_TABLE + "(" + USER_ID_COLUMN + ", " + SUBJECT_ID_COLUMN + ")\n"
            + "VALUES(?, ?)";

    public SubjectJDBCRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    /**
     * Odnajduje przedmiot w bazie danych za pomocą id
     *
     * @param id identyfikator przedmiotu w bazie danych
     * @return obiekt przedmiotu pobranego z bazy danych
     */
    @Override
    public Subject findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new SubjectResultSetExtractor(), id);
    }

    /**
     * Odnajdue wszystkie przedmioty w bazie danych
     *
     * @return lista przedmiotów
     */
    @Override
    public List<Subject> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectRowMapper());
    }

    /**
     * Odnajduje wszystkie przedmioty przypisane do użytkownika(student |
     * wykładowca)
     *
     * @param id identyfikator użytkownika w bazie danych
     * @return lista przedmiotów przypisancyh do użytkownika
     */
    @Override
    public List<Subject> findAllInUser(Long id) {
        return template.query(SQL_FIND_ALL_IN_USER_QUERY, new SubjectRowMapper(), id);

    }

    /**
     * Odnajduje wszystkie przedmioty, które nie są przypisane do
     * użytkownika(student | wykładowca)
     *
     * @param id identyfikator użytkownika w bazie danych
     * @return lista przedmiotów, które nie są przypisane do użytkownika
     */
    @Override
    public List<Subject> findAllNotInUser(Long id) {
        return template.query(SQL_FIND_ALL_NOT_IN_USER_QUERY, new SubjectRowMapper(), id);
    }

    /**
     * Tworzy nowy przedmiot
     *
     * @param subject obiekt przedmiotu, z którego będą czerpane dane przy
     * zapisie w bazie
     * @return wartość logiczna, czy dodawanie przedmiotu powiodło się
     */
    @Override
    public boolean create(Subject subject) {
        template.update(SQL_INSERT_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId());

        return true;
    }

    /**
     * Aktualizuje istniejący przedmiot w bazie danych
     *
     * @param subject obiekt przedmiotu, z którego będą czerpane dane przy
     * aktualiacji w bazie
     * @return wartość logiczna, czy aktualizowanie przzedmiotu powiodło się
     */
    @Override
    public boolean update(Subject subject) {
        template.update(SQL_UPDATE_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId(),
                subject.getId());

        return true;
    }

    /**
     * Przypisuje do użytkownika przedmiot w bazie dancych
     *
     * @param id identyfikator użytkownika
     * @param subject przedmiot, który ma zostać przypisany użytkownikowi
     * @return wartość logiczna, czy przypisywanie przedmiotu powiodło się
     */
    @Override
    public boolean attachToUser(Long id, Subject subject) {
        template.update(SQL_ATTACH_SUBJECT_IN_USER_QUERY, id, subject.getId());

        return true;
    }

    /**
     * Przypisuje użytkownikowi przedmioty w bazie danych
     *
     * @param id identyfikator użytkownika
     * @param subjects przedmioty, które mają zostać przypisane użytkownikowi
     * @return wartość logiczna, czy przypisywanie przedmiotów powiodło się
     */
    @Override
    public boolean attachToUser(Long id, List<Subject> subjects) {
        template.update(SQL_DETACH_SUBJECTS_IN_USER_QUERY, id);
        subjects.forEach(subject -> attachToUser(id, subject));

        return true;
    }

    private static Subject generate(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong(SUBJECT_ID_COLUMN));
        subject.setName(rs.getString(SUBJECT_NAME_COLUMN));
        subject.setEcts(rs.getInt(SUBJECT_ECTS_COLUMN));
        subject.setHours(rs.getInt(SUBJECT_HOURS_COLUMN));

        SubjectType subjectType = new SubjectType();
        subjectType.setId(rs.getLong(SUBJECT_TYPE_ID_COLUMN));
        subjectType.setName(rs.getString(SUBJECT_TYPE_NAME_COLUMN));

        subject.setSubjectType(subjectType);

        return subject;
    }

    private static class SubjectRowMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    private static class SubjectResultSetExtractor implements ResultSetExtractor<Subject> {

        @Override
        public Subject extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
