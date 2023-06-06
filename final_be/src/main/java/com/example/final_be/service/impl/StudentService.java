package com.example.final_be.service.impl;

import com.example.final_be.DTO.ClazzDTO;
import com.example.final_be.DTO.ExpressionDTO;
import com.example.final_be.DTO.ExpressionStatusDTO;
import com.example.final_be.DTO.StudentDTO;
import com.example.final_be.entity.Clazz;
import com.example.final_be.entity.Expression;
import com.example.final_be.entity.ExpressionStatus;
import com.example.final_be.entity.Student;
import com.example.final_be.repository.IClazzRepository;
import com.example.final_be.repository.IExpressionRepository;
import com.example.final_be.repository.IExpressionStatusRepository;
import com.example.final_be.repository.IStudentRepository;
import com.example.final_be.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService implements IStudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IExpressionStatusRepository expressionStatusRepository;
    @Autowired
    private IExpressionRepository expressionRepository;
    @Autowired
    private IClazzRepository clazzRepository;
    @Override
    public Page<StudentDTO> findAllStudent(Pageable pageable, String classId) {
        Page<Student> students = studentRepository.findAllWithClass(pageable, classId);
        List<StudentDTO> studentDTOS = new ArrayList<>();
        StudentDTO studentDTO;
        for (Student student: students) {
            studentDTO = new StudentDTO();
            studentDTO.setClazzDTO(new ClazzDTO());
            studentDTO.setExpressionDTO(new ExpressionDTO());
            studentDTO.getExpressionDTO().setExpressionStatusDTO(new ExpressionStatusDTO());
            BeanUtils.copyProperties(student.getClazz(), studentDTO.getClazzDTO());
            BeanUtils.copyProperties(student.getExpression(), studentDTO.getExpressionDTO());
            BeanUtils.copyProperties(student.getExpression().getExpressionStatus(),
                    studentDTO.getExpressionDTO().getExpressionStatusDTO());
            BeanUtils.copyProperties(student, studentDTO);
            studentDTOS.add(studentDTO);
        }
        return new PageImpl<>(studentDTOS, pageable, students.getTotalElements());
    }

    @Override
    public List<StudentDTO> findTop5PunishedStudent() {
        List<Student> studentList = studentRepository.findTop5PunishedStudent();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        StudentDTO studentDTO;
        for (Student student: studentList) {
            studentDTO = new StudentDTO();
            studentDTO.setClazzDTO(new ClazzDTO());
            studentDTO.setExpressionDTO(new ExpressionDTO());
            BeanUtils.copyProperties(student.getClazz(), studentDTO.getClazzDTO());
            BeanUtils.copyProperties(student.getExpression(), studentDTO.getExpressionDTO());
            BeanUtils.copyProperties(student, studentDTO);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public StudentDTO findById(Integer id) {
        Student student = studentRepository.findById(id).get();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setClazzDTO(new ClazzDTO());
        studentDTO.setExpressionDTO(new ExpressionDTO());
        studentDTO.getExpressionDTO().setExpressionStatusDTO(new ExpressionStatusDTO());
        BeanUtils.copyProperties(student.getClazz(), studentDTO.getClazzDTO());
        BeanUtils.copyProperties(student.getExpression(), studentDTO.getExpressionDTO());
        BeanUtils.copyProperties(student.getExpression().getExpressionStatus(),
                studentDTO.getExpressionDTO().getExpressionStatusDTO());
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }

    @Override
    public void updateExpressionStatus(StudentDTO studentDTO) {
        ExpressionStatus expressionStatus = expressionStatusRepository.findById(1).get();
        Student student = studentRepository.findById(studentDTO.getId()).get();
        student.getExpression().setExpressionStatus(expressionStatus);
        studentRepository.save(student);
    }

    @Override
    public List<StudentDTO> findStudentsUnrewarded() {
        List<Student> studentList = studentRepository.findStudentsUnrewarded();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        StudentDTO studentDTO;
        for (Student student: studentList) {
            studentDTO = new StudentDTO();
            studentDTO.setClazzDTO(new ClazzDTO());
            studentDTO.setExpressionDTO(new ExpressionDTO());
            BeanUtils.copyProperties(student.getClazz(), studentDTO.getClazzDTO());
            BeanUtils.copyProperties(student.getExpression(), studentDTO.getExpressionDTO());
            BeanUtils.copyProperties(student, studentDTO);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public String createStudent(StudentDTO studentDTO) {
        Expression expression = new Expression();
        expressionRepository.save(expression);
        ExpressionStatus expressionStatus = expressionStatusRepository.findById(1).get();
        expression.setExpressionStatus(expressionStatus);
        List<Expression> expressionList = expressionRepository.findAll();
        Expression lastExpression = expressionRepository.findById(expressionList.size() - 1).get();
        Student student = new Student();
        List<Student> studentList = studentRepository.findAll();
        for (Student student1: studentList) {
            if (studentDTO.getEmail().equals(student1.getEmail())) {
                return "Email đã tồn tại";
            }
        }
        Clazz clazz = clazzRepository.findById(studentDTO.getClazzDTO().getId()).get();
        BeanUtils.copyProperties(studentDTO, student);
        int id = studentRepository.getTotalCodeAmount() + 1;
        student.setCode("MSSV-" + id);
        student.setClazz(clazz);
        student.setExpression(lastExpression);
        studentRepository.save(student);
        return "";
    }
}
