package com.school.employment.service;


import com.school.employment.entity.Consultant;
import com.school.employment.repository.ConsultantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    //返回所有咨询师数据
    public List<Consultant> getAllConsultants() {
        return consultantRepository.findAll();
    }

    public List<Consultant> searchConsultant(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return consultantRepository.findAll();
        }
        try{
            Long id = getIdFromString(keyword);
            return consultantRepository.findByNameContainingOrId(keyword, id);
        } catch (Exception e) {
            return consultantRepository.findByNameContaining(keyword);
        }
    }

    private Long getIdFromString(String idString) {
        if(idString == null || idString.trim().isEmpty()) {
            throw new IllegalArgumentException("idString cannot be null or empty");
        }
        String numberStr = idString.replaceAll("[^0-9]", "");
        if (numberStr.isEmpty()) {
            throw new IllegalArgumentException("idString does not contain any numbers");
        }
        try{
            return Long.parseLong(numberStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("idString is not a valid number", e);
        }
    }
}
