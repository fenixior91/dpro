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
        return template.update(SQL_UPDATE_QUERY,
                subject.getName(), subject.getEcts(),
                subject.getHours(), subject.getSubjectType().getId(),
                subject.getId()) > 0;
    }
}
