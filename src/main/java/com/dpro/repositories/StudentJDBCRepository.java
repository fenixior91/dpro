package com.dpro.repositories;

import com.dpro.domains.Student;
import com.dpro.utils.StudentDBUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentJDBCRepository implements StudentRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_BY_ID = "SELECT * FROM user u INNER JOIN student s ON u.id = s.user_id WHERE u.id = ?";
    private static final String SQL_FIND_ALL = "SELECT * FROM user u INNER JOIN student s ON u.id = s.user_id";

    public StudentJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Student findById(Long id) {
        Student student = template.query(SQL_FIND_BY_ID, new StudentDBUtil.StudentResultSetExtractor(), id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = template.query(SQL_FIND_ALL, new StudentDBUtil.StudentRowMapper());
        return students;
    }

    @Override
    public boolean create(Student student) {
        return true;
    }
}
