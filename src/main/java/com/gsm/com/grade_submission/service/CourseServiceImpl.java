package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.Course;
import com.gsm.com.grade_submission.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    @Override
    public Course getCourse(Long id){
        Optional<Course> courseOptional = courseRepository.findById(id);
        if(courseOptional.isEmpty()){
            throw new NullPointerException("Course Id not found");
        }
        return courseOptional.orElseThrow();
    }

    @Override
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course){
        if(getCourse(course.getId()) != null){
            courseRepository.save(course);
        }

        return course;
    }
}
