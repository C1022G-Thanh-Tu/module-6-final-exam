package com.example.final_be.controller;

import com.example.final_be.DTO.ClazzDTO;
import com.example.final_be.service.IClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/class")
public class ClazzRestController {
    @Autowired
    private IClazzService clazzService;
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<ClazzDTO> clazzDTOList = clazzService.findAll();
        return new ResponseEntity<>(clazzDTOList, HttpStatus.OK);
    }
}
