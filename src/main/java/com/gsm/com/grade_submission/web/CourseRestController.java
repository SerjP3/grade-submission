package com.gsm.com.grade_submission.web;

import com.gsm.com.grade_submission.entity.Course;
import com.gsm.com.grade_submission.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseRestController{

    private final CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/api/courses/{id}")
    public Course getCourse(@PathVariable Long id){
        return courseService.getCourse(id);
    }

    @GetMapping("/api/courses")
    public List<Course> getStudents(){
        return courseService.getCourses();
    }
}
