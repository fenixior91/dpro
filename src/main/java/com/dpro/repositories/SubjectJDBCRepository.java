package com.dpro.repositories;

import com.dpro.domains.Subject;
import com.dpro.utils.SubjectDBUtil;
import com.dpro.utils.SubjectTypeDBUtil;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectJDBCRepository implements SubjectRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_PATTERN
            = "SELECT * FROM subject s INNER JOIN subject_type st ON s.%s = st.%s";
    private static final String SQL_FIND_ALL_QUERY
            = String.format(SQL_FIND_ALL_PATTERN, SubjectDBUtil.SUBJECT_TYPE_ID_COLUMN, SubjectTypeDBUtil.ID_COLUMN);

    private static final String SQL_FIND_BY_ID_PATTERN
            = "SELECT * FROM subject s INNER JOIN subject_type st ON s.%s = st.%s WHERE s.%s = ?";
    private static final String SQL_FIND_BY_ID_QUERY
            = String.format(SQL_FIND_BY_ID_PATTERN, SubjectDBUtil.SUBJECT_TYPE_ID_COLUMN, SubjectTypeDBUtil.ID_COLUMN, SubjectDBUtil.ID_COLUMN);

    private static final String SQL_INSERT_PATTERN
            = "INSERT INTO subject(%s, %s, %s, %s) VALUES(?, ?, ?, ?)";
    private static final String SQL_INSERT_QUERY
            = String.format(SQL_INSERT_PATTERN, SubjectDBUtil.NAME_COLUMN, SubjectDBUtil.ECTS_COLUMN, SubjectDBUtil.HOURS_COLUMN, SubjectDBUtil.SUBJECT_TYPE_ID_COLUMN);

    private static final String SQL_UPDATE_PATTERN
            = "UPDATE subject SET %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?";
    private static final String SQL_UPDATE_QUERY
            = String.format(SQL_UPDATE_PATTERN, SubjectDBUtil.NAME_COLUMN, SubjectDBUtil.ECTS_COLUMN, SubjectDBUtil.HOURS_COLUMN, SubjectDBUtil.SUBJECT_TYPE_ID_COLUMN, SubjectDBUtil.ID_COLUMN);

    private static final String FIND_ALL_IN_USER
            = "SELECT s.subject_id, s.subject_name, s.ects, s.hours, st.subject_type_id, st.subject_type_name "
            + "FROM user u "
            + "INNER JOIN user_subject us ON u.user_id = us.user_id AND us.user_id = ? "
            + "INNER JOIN subject s ON s.subject_id = us.subject_id "
            + "INNER JOIN subject_type st ON st.subject_type_id = s.subject_type_id";

    private static final String FIND_ALL_NOT_IN_USER
            = "SELECT s.subject_id, s.subject_name, s.ects, s.hours, st.subject_type_id, st.subject_type_name\n"
            + "FROM subject s INNER JOIN subject_type st ON st.subject_type_id = s.subject_type_id\n"
            + "WHERE s.subject_id NOT IN\n"
            + "(SELECT us.subject_id FROM user_subject us WHERE us.user_id = ?)";

    private static final String SQL_DETACH_SUBJECTS_IN_USER
            = "DELETE FROM user_subject WHERE user_id = ?";

    private static final String SQL_ATTACH_SUBJECT_IN_USER
            = "INSERT INTO user_subject(user_id, subject_id) VALUES(?, ?)";

    public SubjectJDBCRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Subject> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectDBUtil.SubjectRowMapper());
    }

    @Override
    public Subject findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new SubjectDBUtil.SubjectResultSetExtractor(), id);
    }

    @Override
    public boolean create(Subject subject) {
        return template.update(SQL_INSERT_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId()) > 0;
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
        return template.query(FIND_ALL_IN_USER, new SubjectDBUtil.SubjectRowMapper(), id);

    }

    @Override
    public List<Subject> findAllNotInUser(Long id) {
        return template.query(FIND_ALL_NOT_IN_USER, new SubjectDBUtil.SubjectRowMapper(), id);
    }

    @Override
    public boolean attachToUser(Long id, Subject subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean attachToUser(Long id, List<Subject> subjects) {
        template.update(SQL_DETACH_SUBJECTS_IN_USER, id);

        subjects.forEach(subject -> {
            template.update(SQL_ATTACH_SUBJECT_IN_USER, id, subject.getId());
        });

        return true;
    }
}
