package com.dpro.services;

import com.dpro.domains.Student;
import com.dpro.repositories.StudentRepository;
import com.dpro.utils.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @param params parametry studenta otrzymane z formularza, z których będą
     * czerpane dane przy tworzeniu obiektu
     * @return wartość logiczna, czy dodawanie studenta powiodło się
     */
    @Override
    public boolean create(Map<String, String> params) {
        Student student = StudentUtil.generate(params);

        return studentRepository.create(student);
    }

    /**
     * @param params parametry studenta otrzymane z formularza, z których będą
     * czerpane dane przy tworzeniu obiketu
     * @return wartość logiczna, czy aktualizowanie studenta powiodło się
     */
    @Override
    public boolean update(Map<String, String> params) {
        Student student = StudentUtil.generate(params);

        return studentRepository.update(student);
    }
}
