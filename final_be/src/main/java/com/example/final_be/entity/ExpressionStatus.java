package com.example.final_be.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "expression_status")
public class ExpressionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany (mappedBy = "expressionStatus")
    @JsonManagedReference
    private Set<Expression> expressionSet;

    public ExpressionStatus() {
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

    public Set<Expression> getExpressionSet() {
        return expressionSet;
    }

    public void setExpressionSet(Set<Expression> expressionSet) {
        this.expressionSet = expressionSet;
    }
}
