package com.gsm.com.grade_submission.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentGradeDTO{

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private List<CourseGradeDTO> courseGrades;

    public StudentGradeDTO(){

    }

    public StudentGradeDTO(Long studentId, String studentName, String studentEmail, List<CourseGradeDTO> courseGrades) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.courseGrades = courseGrades;
    }

}
