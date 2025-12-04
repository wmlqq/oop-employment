package com.school.employment.repository;

import com.school.employment.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByStudentId(String studentId);

    List<Student> findByNameContainingOrStudentIdContaining(String keyword, String keyword1);
}
