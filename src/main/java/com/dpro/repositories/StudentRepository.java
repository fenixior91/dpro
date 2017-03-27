package com.dpro.repositories;

import com.dpro.domains.Student;
import com.dpro.domains.User;

import java.util.Date;
import java.util.List;

public interface StudentRepository {
	Student findById(Long id);

	Student findByPesel(String pesel);

	Student findByEmail(String email);

	Student findByAlbum(String album);

	List<Student> findAll();

	List<Student> findAllByFirstName(String firstName);

	List<Student> findAllByLastName(String lastName);

	List<Student> findAllByDateOfBirth(Date dateOfBirth);

	List<Student> findAllByEnabled(boolean enabled);
}
