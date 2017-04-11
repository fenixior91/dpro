package com.dpro.repositories;

import com.dpro.domains.Instructor;
import com.dpro.utils.InstructorDBUtil;
import com.dpro.utils.UserDBUtil;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class InstructorJDBCRepository implements InstructorRepository {

    private final JdbcTemplate template;

    public InstructorJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Instructor findById(Long id) {
        return template.query(InstructorDBUtil.SQL_FIND_BY_ID_QUERY, new InstructorDBUtil.InstructorResultSetExtractor(), id);
    }

    @Override
    public List<Instructor> findAll() {
        return template.query(InstructorDBUtil.SQL_FIND_ALL_QUERY, new InstructorDBUtil.InstructorRowMapper());
    }

    @Override
    public boolean create(Instructor instructor) {
        template.update(InstructorDBUtil.SQL_INSERT_ROLE_QUERY, instructor.getUsername());

        template.update(UserDBUtil.SQL_INSERT_USER_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                true, instructor.getEmail(), instructor.getDateOfBirth(), instructor.getPesel());

        template.update(InstructorDBUtil.SQL_INSERT_INSTRUCTOR_QUERY, instructor.getScienceDegree());

        return true;
    }

    @Override
    public boolean update(Instructor instructor) {
        template.update(UserDBUtil.SQL_UPDATE_USER_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                instructor.isEnabled(), instructor.getEmail(),
                instructor.getDateOfBirth(), instructor.getPesel(), instructor.getId());

        template.update(InstructorDBUtil.SQL_UPDATE_INSTRUCTOR_QUERY, instructor.getScienceDegree(), instructor.getId());

        return true;
    }
}