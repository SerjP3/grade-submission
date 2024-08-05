package com.gsm.com.grade_submission.repository;

import com.gsm.com.grade_submission.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}
