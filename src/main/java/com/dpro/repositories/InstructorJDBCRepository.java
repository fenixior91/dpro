package com.dpro.repositories;

import com.dpro.domains.Instructor;
import com.dpro.domains.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class InstructorJDBCRepository implements InstructorRepository {
	JdbcTemplate template;

	public InstructorJDBCRepository(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Instructor> findByScienceDegree(String scienceDegree) {
		return null;
	}

	@Override
	public Instructor findById(Long id) {
		return null;
	}

	@Override
	public Instructor findByPesel(String pesel) {
		return null;
	}

	@Override
	public Instructor findByEmail(String email) {
		return null;
	}

	@Override
	public List<Instructor> findAll() {
		return null;
	}

	@Override
	public List<Instructor> findAllByFirstName(String firstName) {
		return null;
	}

	@Override
	public List<Instructor> findAllByLastName(String lastName) {
		return null;
	}

	@Override
	public List<Instructor> findAllByDateOfBirth(Date dateOfBirth) {
		return null;
	}

	@Override
	public List<Instructor> findAllByEnabled(boolean enabled) {
		return null;
	}
}
