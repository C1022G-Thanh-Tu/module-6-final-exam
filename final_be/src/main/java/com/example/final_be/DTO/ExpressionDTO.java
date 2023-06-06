package com.example.final_be.DTO;

import java.util.Set;

public class ExpressionDTO {
    private Integer id;
    private String name;
    private String reason;
    private String beginDate;
    private Set<StudentDTO> studentDTOSet;
    private ExpressionStatusDTO expressionStatusDTO;

    public ExpressionDTO() {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public Set<StudentDTO> getStudentDTOSet() {
        return studentDTOSet;
    }

    public void setStudentDTOSet(Set<StudentDTO> studentDTOSet) {
        this.studentDTOSet = studentDTOSet;
    }

    public ExpressionStatusDTO getExpressionStatusDTO() {
        return expressionStatusDTO;
    }

    public void setExpressionStatusDTO(ExpressionStatusDTO expressionStatusDTO) {
        this.expressionStatusDTO = expressionStatusDTO;
    }
}
