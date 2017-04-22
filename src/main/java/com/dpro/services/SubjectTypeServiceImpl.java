package com.dpro.services;

import com.dpro.domains.SubjectType;
import com.dpro.repositories.SubjectTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis wspomagający manipulowaniem danymi typu przedmiotu przed zmianą w
 * bazie danych
 *
 * @author Tomasz Truszkowski
 */
@Service
public class SubjectTypeServiceImpl implements SubjectTypeService {

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

    /**
     * @param id identyfikator typu przedmiotu w bazie danych
     * @return obiekt typu przedmiotu pobranego z bazy danych
     */
    @Override
    public SubjectType findById(Long id) {
        return subjectTypeRepository.findById(id);
    }

    /**
     * Odnajdue wszystkie typy przedmiotów w bazie danych
     *
     * @return lista typów przedmiotów
     */
    @Override
    public List<SubjectType> findAll() {
        return subjectTypeRepository.findAll();
    }
}
