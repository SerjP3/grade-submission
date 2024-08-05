package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.Student;
import com.gsm.com.grade_submission.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudentById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()){
            throw new NullPointerException("Student Id not found");
        }
        return optionalStudent.orElseThrow();
    }

    @Override
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student){
       return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student){
        if(getStudentById(student.getId()) != null){
            studentRepository.save(student);
        }
        return student;
    }
}
