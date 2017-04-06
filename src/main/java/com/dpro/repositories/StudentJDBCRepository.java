package com.dpro.repositories;

import com.dpro.domains.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentJDBCRepository implements StudentRepository {
	private JdbcTemplate template;
	
	private static final String SQL_FIND_BY_ID	= "SELECT * FROM user u INNER JOIN student s ON u.id = s.user_id WHERE u.id = ?";
	private static final String SQL_FIND_ALL = "SELECT * FROM user u INNER JOIN student s ON u.id = s.user_id";
	
	public StudentJDBCRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public Student findById(Long id) {
		Student student = template.query(SQL_FIND_BY_ID, new StudentResultSetExtractor(), id);
		return student;
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = template.query(SQL_FIND_ALL, new StudentRowMapper());
		return students;
	}

	private Student generate(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getLong("id"));
		student.setUsername(resultSet.getString("username"));
		student.setPassword(resultSet.getString("password"));
		student.setEnabled(resultSet.getBoolean("enabled"));
		student.setEmail(resultSet.getString("email"));
		student.setFirstName(resultSet.getString("first_name"));
		student.setLastName(resultSet.getString("last_name"));
		student.setDateOfBirth(resultSet.getDate("date_of_birth"));
		student.setAlbum(resultSet.getString("album"));
		return student;
	}

	private class StudentRowMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet resultSet, int i) throws SQLException {
			return generate(resultSet);
		}
	}

	private class StudentResultSetExtractor implements ResultSetExtractor<Student> {
		@Override
		public Student extractData(ResultSet resultSet) throws SQLException, DataAccessException {
			if (resultSet.next()) {
				return generate(resultSet);
			}

			return null;
		}
	}
}
