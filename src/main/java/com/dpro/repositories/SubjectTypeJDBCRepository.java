package com.dpro.repositories;

import com.dpro.domains.SubjectType;
import com.dpro.utils.SubjectTypeDBUtil;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectTypeJDBCRepository implements SubjectTypeRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM subject_type";

    private static final String SQL_FIND_BY_ID_PATTERN
            = "SELECT * FROM subject_type WHERE %s = ?";
    private static final String SQL_FIND_BY_ID_QUERY
            = String.format(SQL_FIND_BY_ID_PATTERN, SubjectTypeDBUtil.ID_COLUMN);

    private static final String SQL_INSERT_PATTERN
            = "INSERT INTO subject_type(%s) VALUES(?)";
    private static final String SQL_INSERT_QUERY
            = String.format(SQL_INSERT_PATTERN, SubjectTypeDBUtil.NAME_COLUMN);

    public SubjectTypeJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<SubjectType> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectTypeDBUtil.SubjectTypeRowMapper());
    }

    @Override
    public SubjectType findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new SubjectTypeDBUtil.SubjectTypeResultSetExtractor(), id);
    }

    @Override
    public boolean create(SubjectType subjectType) {
        return template.update(SQL_INSERT_QUERY, subjectType.getName()) > 0;
    }
}
