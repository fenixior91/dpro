package com.dpro.repositories;

import com.dpro.domains.Instructor;

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
public class InstructorJDBCRepository implements InstructorRepository {
	private JdbcTemplate template;
	
	private final static String SQL_FIND_BY_ID = "SELECT * FROM user u INNER JOIN instructor i ON u.id = i.user_id WHERE u.id = ?";
	private final static String SQL_FIND_ALL = "SELECT * FROM user u INNER JOIN instructor i ON u.id = i.user_id";
	public InstructorJDBCRepository(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Override
	public Instructor findById(Long id) {
		return template.query(SQL_FIND_BY_ID, new InstructorResultSetExtractor(), id);
	}
	
	@Override
	public List<Instructor> findAll() {
		return template.query(SQL_FIND_ALL, new InstructorRowMapper());
	}
	
	private Instructor generate(ResultSet resultSet) throws SQLException {
		Instructor instructor = new Instructor();
		instructor.setId(resultSet.getLong("id"));
		instructor.setUsername(resultSet.getString("username"));
		instructor.setPassword(resultSet.getString("password"));
		instructor.setEnabled(resultSet.getBoolean("enabled"));
		instructor.setEmail(resultSet.getString("email"));
		instructor.setFirstName(resultSet.getString("first_name"));
		instructor.setLastName(resultSet.getString("last_name"));
		instructor.setDateOfBirth(resultSet.getDate("date_of_birth"));
		instructor.setScienceDegree(resultSet.getString("science_degree"));
		return instructor;
	}

	private class InstructorRowMapper implements RowMapper<Instructor> {

		@Override
		public Instructor mapRow(ResultSet resultSet, int i) throws SQLException {
			return generate(resultSet);
		}
	}

	private class InstructorResultSetExtractor implements ResultSetExtractor<Instructor> {
		@Override
		public Instructor extractData(ResultSet resultSet) throws SQLException, DataAccessException {
			if (resultSet.next()) {
				return generate(resultSet);
			}

			return null;
		}
	}
}
