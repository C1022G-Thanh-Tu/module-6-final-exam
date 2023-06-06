package com.example.final_be.repository;

import com.example.final_be.entity.ExpressionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpressionStatusRepository extends JpaRepository<ExpressionStatus, Integer> {
}
