package com.dpro.repositories;

import com.dpro.domains.Subject;
import com.dpro.domains.SubjectType;
import com.dpro.utils.SubjectTypeDBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectJDBCRepository implements SubjectRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM subject s\n"
            + "INNER JOIN subject_type st ON s." + SUBJECT_TYPE_ID_COLUMN + " = st." + SUBJECT_TYPE_ID_COLUMN;

    private static final String SQL_FIND_BY_ID_QUERY
            = "SELECT * FROM subject s\n"
            + "INNER JOIN subject_type st ON s." + SUBJECT_TYPE_ID_COLUMN + " = st." + SUBJECT_TYPE_ID_COLUMN + "\n"
            + " WHERE s." + SUBJECT_TYPE_ID_COLUMN + " = ?";

    private static final String SQL_INSERT_QUERY
            = "INSERT INTO subject(" + NAME_COLUMN + ", " + ECTS_COLUMN + ", " + HOURS_COLUMN + ", " + SUBJECT_TYPE_ID_COLUMN + ")\n"
            + "VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE_QUERY
            = "UPDATE subject\n"
            + "SET " + NAME_COLUMN + " = ?, " + ECTS_COLUMN + " = ?, " + HOURS_COLUMN + " = ?, " + SUBJECT_TYPE_ID_COLUMN + " = ?\n"
            + "WHERE " + SUBJECT_ID_COLUMN + " = ?\n";

    private static final String SQL_FIND_ALL_IN_USER_QUERY
            = "SELECT s." + SUBJECT_ID_COLUMN + ", s." + NAME_COLUMN + ", s." + ECTS_COLUMN + ", s." + HOURS_COLUMN + ", st." + SUBJECT_TYPE_ID_COLUMN + ", st." + SUBJECT_TYPE_NAME_COLUMN + "\n"
            + "FROM user u\n"
            + "INNER JOIN user_subject us ON u." + USER_ID + " = us." + USER_ID + " AND us." + USER_ID + " = ?\n"
            + "INNER JOIN subject s ON s." + SUBJECT_ID_COLUMN + " = us." + SUBJECT_ID_COLUMN + "\n"
            + "INNER JOIN subject_type st ON st." + SUBJECT_TYPE_ID_COLUMN + " = s." + SUBJECT_TYPE_ID_COLUMN;

    private static final String SQL_FIND_ALL_NOT_IN_USER_QUERY
            = "SELECT s." + SUBJECT_ID_COLUMN + ", s." + NAME_COLUMN + ", s." + ECTS_COLUMN + ", s." + HOURS_COLUMN + ", st." + SUBJECT_TYPE_ID_COLUMN + ", st. " + SUBJECT_TYPE_NAME_COLUMN + "\n"
            + "FROM subject s\n"
            + "INNER JOIN subject_type st ON st." + SUBJECT_TYPE_ID_COLUMN + " = s." + SUBJECT_TYPE_ID_COLUMN + "\n"
            + "WHERE s." + SUBJECT_ID_COLUMN + " NOT IN\n"
            + "(SELECT us." + SUBJECT_ID_COLUMN + " FROM user_subject us WHERE us." + USER_ID + " = ?)";

    private static final String SQL_DETACH_SUBJECTS_IN_USER_QUERY
            = "DELETE FROM user_subject\n"
            + "WHERE " + USER_ID + " = ?";

    private static final String SQL_ATTACH_SUBJECT_IN_USER_QUERY
            = "INSERT INTO user_subject(" + USER_ID + ", " + SUBJECT_ID_COLUMN + ")\n"
            + "VALUES(?, ?)";

    public SubjectJDBCRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Subject> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectRowMapper());
    }

    @Override
    public Subject findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new SubjectResultSetExtractor(), id);
    }

    @Override
    public boolean create(Subject subject) {
        template.update(SQL_INSERT_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId());

        return true;
    }

    @Override
    public boolean update(Subject subject) {
        template.update(SQL_UPDATE_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId(),
                subject.getId());

        return true;
    }

    @Override
    public List<Subject> findAllInUser(Long id) {
        return template.query(SQL_FIND_ALL_IN_USER_QUERY, new SubjectRowMapper(), id);

    }

    @Override
    public List<Subject> findAllNotInUser(Long id) {
        return template.query(SQL_FIND_ALL_NOT_IN_USER_QUERY, new SubjectRowMapper(), id);
    }

    @Override
    public boolean attachToUser(Long id, Subject subject) {
        template.update(SQL_ATTACH_SUBJECT_IN_USER_QUERY, id, subject.getId());

        return true;
    }

    @Override
    public boolean attachToUser(Long id, List<Subject> subjects) {
        template.update(SQL_DETACH_SUBJECTS_IN_USER_QUERY, id);
        subjects.forEach(subject -> attachToUser(id, subject));

        return true;
    }

    public static Subject generate(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong(SUBJECT_ID_COLUMN));
        subject.setName(rs.getString(NAME_COLUMN));
        subject.setEcts(rs.getInt(ECTS_COLUMN));
        subject.setHours(rs.getInt(HOURS_COLUMN));

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
