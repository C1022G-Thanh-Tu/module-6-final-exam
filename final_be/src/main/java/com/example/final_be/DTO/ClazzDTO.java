package com.example.final_be.DTO;

import java.util.Set;

public class ClazzDTO {
    private Integer id;
    private String name;
    private String code;
    private String startDate;
    private String endDate;
    private Set<StudentDTO> studentDTOSet;

    public ClazzDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<StudentDTO> getStudentDTOSet() {
        return studentDTOSet;
    }

    public void setStudentDTOSet(Set<StudentDTO> studentDTOSet) {
        this.studentDTOSet = studentDTOSet;
    }
}
