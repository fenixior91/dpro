package com.dpro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpro.domains.Instructor;
import com.dpro.repositories.InstructorRepository;
import com.dpro.utils.InstructorUtil;
import java.util.Map;

/**
 * Serwis wspomagający manipulowaniem danymi wykadowcy przed zmianą w bazie
 * danych
 *
 * @author Tomasz Truszkowski
 */
@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    /**
     * @param id ientyfikator wykładowcy
     * @return obiekt wykładowcy pobranego z repozytorium
     */
    @Override
    public Instructor findById(Long id) {
        return instructorRepository.findById(id);
    }

    /**
     * @return lista wykładowców
     */
    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    /**
     * @param params parametry wykładowcy otrzymane z formularza, z których będą
     * czerpane dane przy tworzeniu obiektu
     * @return wartość logiczna, czy dodawanie wykładowcy powiodło się
     */
    @Override
    public boolean create(Map<String, String> params) {
        Instructor instructor = InstructorUtil.generate(params);

        return instructorRepository.create(instructor);
    }

    /**
     * @param params parametry wykładowcy otrzymane z formularza, z których będą
     * czerpane dane przy tworzeniu obiektu
     * @return wartość logiczna, czy aktualizowanie wykładowcy powiodło się
     */
    @Override
    public boolean update(Map<String, String> params) {
        Instructor instructor = InstructorUtil.generate(params);

        return instructorRepository.update(instructor);
    }
}
