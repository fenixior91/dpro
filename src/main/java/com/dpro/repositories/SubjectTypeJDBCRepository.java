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

    private static final String SQL_FIND_ALL = "SELECT * FROM subject_type";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM subject_type WHERE id = ?";
    private static final String SQL_CREATE = "INSERT INTO subject_type(name) VALUES(?)";

    public SubjectTypeJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<SubjectType> findAll() {
        return template.query(SQL_FIND_ALL, new SubjectTypeDBUtil.SubjectTypeRowMapper());
    }

    @Override
    public SubjectType findById(Long id) {
        return template.query(SQL_FIND_BY_ID, new SubjectTypeDBUtil.SubjectTypeResultSetExtractor(), id);
    }

    @Override
    public boolean create(SubjectType subjectType) {
        return template.update(SQL_CREATE, subjectType.getName()) > 0;
    }
}
