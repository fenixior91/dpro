package com.dpro.services;

import com.dpro.domains.SubjectType;
import com.dpro.repositories.SubjectTypeRepository;
import com.dpro.utils.SubjectTypeUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectTypeServiceImpl implements SubjectTypeService {

    @Autowired
    SubjectTypeRepository subjectTypeRepository;
    
    @Override
    public List<SubjectType> findAll() {
        return subjectTypeRepository.findAll();
    }

    @Override
    public SubjectType findById(Long id) {
        return subjectTypeRepository.findById(id);
    }

    @Override
    public boolean create(Map<String, String> params) {
        SubjectType subjectType = SubjectTypeUtil.generate(params);
        
        return subjectTypeRepository.create(subjectType);
    }
}
