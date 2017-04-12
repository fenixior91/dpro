package com.dpro.repositories;

import com.dpro.domains.Student;
import com.dpro.utils.StudentDBUtil;
import com.dpro.utils.UserDBUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentJDBCRepository implements StudentRepository {

    private final JdbcTemplate template;

    public StudentJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Student findById(Long id) {
        Student student = template.query(StudentDBUtil.SQL_FIND_BY_ID_QUERY, new StudentDBUtil.StudentResultSetExtractor(), id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = template.query(StudentDBUtil.SQL_FIND_ALL_QUERY, new StudentDBUtil.StudentRowMapper());
        return students;
    }

    @Override
    public boolean create(Student student) {
        if (insertStudentRole(student) < 1) {
            return false;
        }

        if (insertUser(student) < 1) {
            return false;
        }

        return insertStudent(student) >= 1;
    }

    @Override
    public boolean update(Student student) {
        if (updateUser(student) < 1) {
            return false;
        }

        return updateStudent(student) >= 1;
    }

    private int insertStudentRole(Student student) {
        System.out.println(student);
        return template.update(StudentDBUtil.SQL_INSERT_ROLE_QUERY, student.getUsername());
    }

    private int insertUser(Student student) {
        return template.update(UserDBUtil.SQL_INSERT_QUERY,
                student.getUsername(), student.getPassword(),
                student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getDateOfBirth(), student.getPesel());
    }

    private int insertStudent(Student student) {
        return template.update(StudentDBUtil.SQL_INSERT_QUERY, student.getAlbum());
    }

    private int updateUser(Student student) {
        return template.update(UserDBUtil.SQL_UPDATE_QUERY,
                student.getUsername(), student.getPassword(),
                student.getFirstName(), student.getLastName(),
                student.isEnabled(), student.getEmail(),
                student.getDateOfBirth(), student.getPesel(), student.getId());
    }

    private int updateStudent(Student student) {
        return template.update(StudentDBUtil.SQL_UPDATE_QUERY, student.getAlbum(), student.getId());
    }
}
