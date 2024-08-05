package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService{

    Course getCourse(Long id);
    List<Course> getCourses();
    Course saveCourse(Course course);
    Course updateCourse(Course course);
}
