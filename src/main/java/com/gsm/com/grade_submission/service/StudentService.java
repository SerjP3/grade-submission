package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService{

    Student getStudentById(Long id);
    List<Student> getStudents();
    Student saveStudent(Student student);
    Student updateStudent(Student student);
}
