package com.dpro.services;

import com.dpro.domains.Subject;
import com.dpro.repositories.SubjectRepository;
import com.dpro.repositories.SubjectTypeRepository;
import com.dpro.utils.SubjectUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serwis wspomagający manipulowaniem danymi przedmiotu przed zmianą w bazie
 * danych
 *
 * @author Tomasz Truszkowski
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

    /**
     * @param id identyfikator przedmiotu w bazie danych
     * @return obiekt przedmiotu pobranego z bazy danych
     */
    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }

    /**
     * @return lista przedmiotów
     */
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    /**
     * @param id identyfikator użytkownika w bazie danych
     * @return lista przedmiotów przypisancyh do użytkownika
     */
    @Override
    public List<Subject> findAllInUser(Long id) {
        return subjectRepository.findAllInUser(id);
    }

    /**
     * @param id identyfikator użytkownika w bazie danych
     * @return lista przedmiotów, które nie są przypisane do użytkownika
     */
    @Override
    public List<Subject> findAllNotInUser(Long id) {
        return subjectRepository.findAllNotInUser(id);
    }

    /**
     * Tworzy nowy przedmiot
     *
     * @param params parametry przedmiotu otrzymane z formularza, z których będą
     * czerpane dane przy tworzeniu obiektu
     * @return wartość logiczna, czy dodawanie przedmiotu powiodło się
     */
    @Override
    public boolean create(Map<String, String> params) {
        Subject subject = SubjectUtil.generate(params);
        subject.setSubjectType(subjectTypeRepository.findById(Long.parseLong(params.get(SubjectUtil.SUBJECT_TYPE_ID))));

        return subjectRepository.create(subject);
    }

    /**
     * Aktualizuje istniejący przedmiot w bazie danych
     *
     * @param params parametry przedmiotu otrzymane z formularza , z których
     * będą czerpane dane przy tworzeniu obiektu
     * @return wartość logiczna, czy aktualizowanie przzedmiotu powiodło się
     */
    @Override
    public boolean update(Map<String, String> params) {
        Subject subject = SubjectUtil.generate(params);
        subject.setSubjectType(subjectTypeRepository.findById(Long.parseLong(params.get(SubjectUtil.SUBJECT_TYPE_ID))));

        return subjectRepository.update(subject);
    }

    /**
     * @param id identyfikator użytkownika
     * @param subject przedmiot, który ma zostać przypisany użytkownikowi
     * @return wartość logiczna, czy przypisywanie przedmiotu powiodło się
     */
    @Override
    public boolean attachToUser(Long id, Subject subject) {
        return subjectRepository.attachToUser(id, subject);
    }

    /**
     * @param id identyfikator użytkownika
     * @param subjects przedmioty, które mają zostać przypisane użytkownikowi
     * @return wartość logiczna, czy przypisywanie przedmiotów powiodło się
     */
    @Override
    public boolean attachToUser(Long id, List<Subject> subjects) {
        return subjectRepository.attachToUser(id, subjects);
    }
}
