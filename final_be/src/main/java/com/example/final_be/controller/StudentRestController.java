package com.example.final_be.controller;

import com.example.final_be.DTO.StudentDTO;
import com.example.final_be.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentRestController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<Page<StudentDTO>> findAll(@PageableDefault(sort = {"id"},direction = Sort.Direction.DESC, size = 5) Pageable pageable,
                                     @RequestParam(required = false ,defaultValue = "") String classId) {
        Page<StudentDTO> studentDTOS = studentService.findAllStudent(pageable, classId);
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping("/top-5")
    public ResponseEntity<?> findTop5PunishedStudent () {
        List<StudentDTO> studentDTOS = studentService.findTop5PunishedStudent();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping("/unrewarded")
    public ResponseEntity<?> findStudentsUnrewarded () {
        List<StudentDTO> studentDTOS = studentService.findStudentsUnrewarded();
        return new ResponseEntity<>(studentDTOS, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findDetail(@PathVariable Integer id) {
        StudentDTO studentDTO = studentService.findById(id);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDTO studentDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            String msg = studentService.createStudent(studentDTO);
            if (!Objects.equals(msg, "")) {
                return new ResponseEntity<>(msg,  HttpStatus.BAD_REQUEST);
            }
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                if (!map.containsKey(error.getField())) {
                    map.put(error.getField(), error.getDefaultMessage());
                }
            }
            return new ResponseEntity<>(map,  HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<?> updateExpressionStatus (@RequestBody StudentDTO studentDTO) {
        studentService.updateExpressionStatus(studentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
