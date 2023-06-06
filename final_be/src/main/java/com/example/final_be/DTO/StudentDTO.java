package com.example.final_be.DTO;

import javax.validation.constraints.NotBlank;

public class StudentDTO {
    private Integer id;
    private String code;
    @NotBlank(message = "Không được bỏ trống")
    private String name;
    @NotBlank(message = "Không được bỏ trống")
    private String dateOfBirth;
    @NotBlank(message = "Không được bỏ trống")
    private String email;
    @NotBlank(message = "Không được bỏ trống")
    private String identityNumber;
    private ClazzDTO clazzDTO;
    private ExpressionDTO expressionDTO;

    public StudentDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public ClazzDTO getClazzDTO() {
        return clazzDTO;
    }

    public void setClazzDTO(ClazzDTO clazzDTO) {
        this.clazzDTO = clazzDTO;
    }

    public ExpressionDTO getExpressionDTO() {
        return expressionDTO;
    }

    public void setExpressionDTO(ExpressionDTO expressionDTO) {
        this.expressionDTO = expressionDTO;
    }
}
