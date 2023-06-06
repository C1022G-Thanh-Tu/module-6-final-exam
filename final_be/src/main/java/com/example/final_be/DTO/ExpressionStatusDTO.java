package com.example.final_be.DTO;

import java.util.Set;

public class ExpressionStatusDTO {
    private Integer id;
    private String name;
    Set<ExpressionDTO> expressionDTOSet;

    public ExpressionStatusDTO() {
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

    public Set<ExpressionDTO> getExpressionDTOSet() {
        return expressionDTOSet;
    }

    public void setExpressionDTOSet(Set<ExpressionDTO> expressionDTOSet) {
        this.expressionDTOSet = expressionDTOSet;
    }
}
