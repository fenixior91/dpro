package com.dpro.services;

import com.dpro.domains.Subject;
import com.dpro.domains.User;
import com.dpro.repositories.SubjectRepository;
import com.dpro.repositories.SubjectTypeRepository;
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
    public List<Subject> findAllByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Subject subject) {
        return subjectRepository.create(subject);
    }

    @Override
    public boolean create(Map<String, String> params) {
        Subject subject = new Subject();
        subject.setName(params.get("subjectName"));
        subject.setEcts(Integer.parseInt(params.get("ects")));
        subject.setHours(Integer.parseInt(params.get("hours")));
        subject.setSubjectType(subjectTypeRepository.findById(Long.parseLong(params.get("subjectType"))));

        System.out.println(subject.getSubjectType().getName());
        return true;
//        return subjectRepository.create(subject);
    }
}
