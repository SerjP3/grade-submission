package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.Grade;
import com.gsm.com.grade_submission.entity.StudentGradeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService{

    Grade getGrade(Long id);
    List<Grade> getGrades();
    void saveGrade(Grade grade);
    List<StudentGradeDTO> getAllStudentGrades();
}
