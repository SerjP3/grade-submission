package com.gsm.com.grade_submission.web;

import com.gsm.com.grade_submission.entity.Student;
import com.gsm.com.grade_submission.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentRestController{

    private final StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/api/students/{id}")
    public Student getStudent(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/api/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
}
