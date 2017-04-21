package com.dpro.services;

import com.dpro.domains.Student;
import com.dpro.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis wspomagający manipulowaniem danymi studenta przed zmianą w bazie
 * danych
 *
 * @author Tomasz Truszkowski
 */
@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * @param id ientyfikator studenta
     * @return obiekt studenta pobranego z bazy danych
     */
    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * @return lista studentów
     */
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * @param student parametry studenta otrzymane z formularza
     * @return wartość logiczna, czy dodawanie studenta powiodło się
     */
    @Override
    public boolean create(Student student) {
        return studentRepository.create(student);
    }

    /**
     * @param student parametry studenta otrzymane z formularza
     * @return wartość logiczna, czy aktualizowanie studenta powiodło się
     */
    @Override
    public boolean update(Student student) {
        return studentRepository.update(student);
    }
}
