package com.example.final_be.repository;

import com.example.final_be.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select * from student where class_id like concat ('%', :classId, '%')", nativeQuery = true)
    Page<Student> findAllWithClass(Pageable pageable, @Param("classId") String classId);
    @Query(value = "select * from student order by punishment_count desc limit 5", nativeQuery = true)
    List<Student> findTop5PunishedStudent();
    @Query(value = "select * from student where reward_count = 0", nativeQuery = true)
    List<Student> findStudentsUnrewarded();
    @Query(value = "select count(code) from student", nativeQuery = true)
    Integer getTotalCodeAmount();
}
