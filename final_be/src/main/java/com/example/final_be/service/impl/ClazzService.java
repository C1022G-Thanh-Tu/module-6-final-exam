package com.example.final_be.service.impl;

import com.example.final_be.DTO.ClazzDTO;
import com.example.final_be.entity.Clazz;
import com.example.final_be.repository.IClazzRepository;
import com.example.final_be.service.IClazzService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private IClazzRepository clazzRepository;

    @Override
    public List<ClazzDTO> findAll() {
        List<Clazz> clazzList = clazzRepository.findAll();
        List<ClazzDTO> clazzDTOList = new ArrayList<>();
        ClazzDTO clazzDTO;
        for (Clazz clazz: clazzList) {
            clazzDTO = new ClazzDTO();
            BeanUtils.copyProperties(clazz, clazzDTO);
            clazzDTOList.add(clazzDTO);
        }
        return clazzDTOList;
    }
}
