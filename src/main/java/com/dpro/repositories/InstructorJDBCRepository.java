package com.dpro.repositories;

import com.dpro.domains.Instructor;
import com.dpro.domains.Subject;
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
        if (insertInstructorRole(instructor) < 1) {
            return false;
        }

        if (insertUser(instructor) < 1) {
            return false;
        }

        return insertInstructor(instructor) >= 1;
    }

    @Override
    public boolean update(Instructor instructor) {
        if (updateUser(instructor) < 1) {
            return false;
        }

        return updateInstructor(instructor) >= 1;
    }

    private int insertInstructorRole(Instructor instructor) {
        return template.update(InstructorDBUtil.SQL_INSERT_ROLE_QUERY, instructor.getUsername());
    }

    private int insertUser(Instructor instructor) {
        return template.update(UserDBUtil.SQL_INSERT_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                instructor.getEmail(), instructor.getDateOfBirth(), instructor.getPesel());
    }

    private int insertInstructor(Instructor instructor) {
        return template.update(InstructorDBUtil.SQL_INSERT_QUERY, instructor.getScienceDegree());
    }

    private int updateUser(Instructor instructor) {
        return template.update(UserDBUtil.SQL_UPDATE_QUERY,
                instructor.getUsername(), instructor.getPassword(),
                instructor.getFirstName(), instructor.getLastName(),
                instructor.isEnabled(), instructor.getEmail(),
                instructor.getDateOfBirth(), instructor.getPesel(), instructor.getId());
    }

    private int updateInstructor(Instructor instructor) {
        return template.update(InstructorDBUtil.SQL_UPDATE_QUERY,
                instructor.getScienceDegree(),
                instructor.getId()
        );
    }

    @Override
    public boolean attach(Subject subject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean attach(List<Subject> subjects) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
