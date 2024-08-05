package com.gsm.com.grade_submission.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CourseGradeDTO {

    private Long courseId;
    private Long gradeId;
    private String courseName;
    private List<Integer> scores;

    public CourseGradeDTO(Long gradeId, String courseName, List<Integer> scores, Long courseId) {
        this.courseId = courseId;
        this.gradeId = gradeId;
        this.courseName = courseName;
        this.scores = scores;
    }

}