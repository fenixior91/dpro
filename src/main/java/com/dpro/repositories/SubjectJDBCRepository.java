package com.dpro.repositories;

import com.dpro.domains.Subject;
import com.dpro.domains.User;
import com.dpro.utils.SubjectDBUtil;
import com.dpro.utils.SubjectTypeUtil;
import com.dpro.utils.SubjectUtil;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectJDBCRepository implements SubjectRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_PATTERN
            = "SELECT %s, %s, %s, %s, %s, %s FROM subject s INNER JOIN subject_type st ON s.%s = st.%s";
    private static final String SQL_FIND_ALL_QUERY
            = String.format(SQL_FIND_ALL_PATTERN, SubjectUtil.ID, SubjectUtil.NAME, SubjectUtil.ECTS, SubjectUtil.HOURS, SubjectTypeUtil.ID, SubjectTypeUtil.NAME, SubjectTypeUtil.ID, SubjectTypeUtil.ID);

    private static final String SQL_FIND_BY_ID
            = "SELECT s.id, s.name, s.ects, s.hours, st.type FROM subject s INNER JOIN subject_type st ON s.subject_type_id = st.id WHERE s.id = ?";

    private static final String SQL_CREATE_SUBJECT
            = "INSERT INTO subject(name, ects, hours, subject_type_id) VALUES(?, ?, ?, ?)";

    private static final String SQL_CREATE_SUBJECT_TYPE
            = "INSERT INTO subject_type(type) VALUES(?)";

    public SubjectJDBCRepository(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Subject> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectDBUtil.SubjectRowMapper());
    }

    @Override
    public Subject findById(Long id) {
        return template.query(SQL_FIND_BY_ID, new SubjectDBUtil.SubjectResultSetExtractor(), id);
    }

    @Override
    public List<Subject> findAllByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Subject subject) {
        return template.update(SQL_CREATE_SUBJECT, subject.getName(), subject.getEcts(), subject.getHours(), subject.getSubjectType().getId()) >= 1;
    }
}
