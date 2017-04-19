package com.dpro.services;

import com.dpro.domains.Subject;
import com.dpro.repositories.SubjectRepository;
import com.dpro.repositories.SubjectTypeRepository;
import com.dpro.utils.SubjectUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectTypeRepository subjectTypeRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id);
    }

    @Override
    public boolean create(Map<String, String> params) {
        Subject subject = SubjectUtil.generate(params);
        subject.setSubjectType(subjectTypeRepository.findById(Long.parseLong(params.get(SubjectUtil.SUBJECT_TYPE_ID))));

        return subjectRepository.create(subject);
    }

    @Override
    public boolean update(Map<String, String> params) {
        Subject subject = SubjectUtil.generate(params);
        subject.setSubjectType(subjectTypeRepository.findById(Long.parseLong(params.get(SubjectUtil.SUBJECT_TYPE_ID))));

        return subjectRepository.update(subject);
    }

    @Override
    public List<Subject> findAllInUser(Long id) {
        return subjectRepository.findAllInUser(id);
    }

    @Override
    public List<Subject> findAllNotInUser(Long id) {
        return subjectRepository.findAllNotInUser(id);
    }
}
