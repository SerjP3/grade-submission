package com.gsm.com.grade_submission.service;

import com.gsm.com.grade_submission.entity.*;
import com.gsm.com.grade_submission.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GradeServiceImpl implements GradeService{

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Grade getGrade(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        return optionalGrade.orElseThrow();
    }

    @Override
    public List<Grade> getGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public void saveGrade(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public List<StudentGradeDTO> getAllStudentGrades() {
        List<Grade> grades = gradeRepository.findAll();

        Map<Long, StudentGradeDTO> studentGradeMap = new HashMap<>();

        for(Grade grade : grades){
            Student student = grade.getStudent();
            Course course = grade.getCourse();
            CourseGradeDTO courseGradeDTO = new CourseGradeDTO(grade.getId(), course.getSubject(), grade.getScores(), course.getId());

            if(!studentGradeMap.containsKey(student.getId())){
                StudentGradeDTO studentGradeDTO = new StudentGradeDTO();
                studentGradeDTO.setStudentId(student.getId());
                studentGradeDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
                studentGradeDTO.setStudentEmail(student.getEmail());
                studentGradeDTO.setCourseGrades(new ArrayList<>());
                studentGradeDTO.getCourseGrades().add(courseGradeDTO);
                studentGradeMap.put(student.getId(), studentGradeDTO);
            } else{
                studentGradeMap.get(student.getId()).getCourseGrades().add(courseGradeDTO);
            }
        }

        return new ArrayList<>(studentGradeMap.values());
    }

}
