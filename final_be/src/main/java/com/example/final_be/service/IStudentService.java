package com.example.final_be.service;

import com.example.final_be.DTO.StudentDTO;
import com.example.final_be.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStudentService {
    Page<StudentDTO> findAllStudent(Pageable pageable, String classId);
    List<StudentDTO> findTop5PunishedStudent();
    StudentDTO findById(Integer id);
    void updateExpressionStatus(StudentDTO studentDTO);
    List<StudentDTO> findStudentsUnrewarded();
    String createStudent(StudentDTO studentDTO);
}
