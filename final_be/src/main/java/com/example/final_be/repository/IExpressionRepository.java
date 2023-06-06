package com.example.final_be.repository;

import com.example.final_be.entity.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpressionRepository extends JpaRepository<Expression, Integer> {
}
