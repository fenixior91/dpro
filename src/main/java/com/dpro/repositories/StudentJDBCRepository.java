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
import java.util.Date;
import java.util.List;

@Repository
public class StudentJDBCRepository implements StudentRepository {
	JdbcTemplate template;
	
	private static final String SQL_FIND_BY_ID	= "SELECT * FROM user u INNER JOIN student s ON u.id = s.user_id && u.id = ?";
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
	public Student findByPesel(String pesel) {
		return null;
	}

	@Override
	public Student findByEmail(String email) {
		return null;
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = template.query(SQL_FIND_ALL, new StudentRowMapper());
		return students;
	}

	@Override
	public List<Student> findAllByFirstName(String firstName) {
		return null;
	}

	@Override
	public List<Student> findAllByLastName(String lastName) {
		return null;
	}

	@Override
	public List<Student> findAllByDateOfBirth(Date dateOfBirth) {
		return null;
	}

	@Override
	public List<Student> findAllByEnabled(boolean enabled) {
		return null;
	}

	@Override
	public Student findByAlbum(String album) {
		return null;
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
