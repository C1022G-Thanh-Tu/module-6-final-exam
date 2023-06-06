package com.example.final_be.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "expression")
public class Expression {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String reason;
    @Column(name = "begin_date")
    private String beginDate;
    @OneToMany(mappedBy = "expression")
    @JsonManagedReference
    private Set<Student> studentSet;
    @ManyToOne
    @JoinColumn(name = "expression_status_id")
    @JsonBackReference
    private ExpressionStatus expressionStatus;

    public Expression() {
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

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public ExpressionStatus getExpressionStatus() {
        return expressionStatus;
    }

    public void setExpressionStatus(ExpressionStatus expressionStatus) {
        this.expressionStatus = expressionStatus;
    }
}
