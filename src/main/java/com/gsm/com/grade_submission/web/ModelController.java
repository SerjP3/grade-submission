package com.gsm.com.grade_submission.web;

import com.gsm.com.grade_submission.entity.Course;
import com.gsm.com.grade_submission.entity.Grade;
import com.gsm.com.grade_submission.entity.Student;
import com.gsm.com.grade_submission.entity.StudentGradeDTO;
import com.gsm.com.grade_submission.service.CourseService;
import com.gsm.com.grade_submission.service.GradeService;
import com.gsm.com.grade_submission.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ModelController{

    private final CourseService courseService;
    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public ModelController(CourseService courseService, StudentService studentService, GradeService gradeService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("dateTime", java.time.LocalDateTime.now().format(formatter));
        return "home";
    }

    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) Long studentId, @RequestParam(required = false) Long courseId, @RequestParam(required = false) Long gradeId, @RequestParam(required = false, defaultValue = "false") boolean updating) {
        List<Course> courseList = courseService.getCourses();
        Student student = studentId == null ? new Student() : studentService.getStudentById(studentId);
        Grade grade = gradeId == null ? new Grade() : gradeService.getGrade(gradeId);
        model.addAttribute("courses", courseList);
        model.addAttribute("student", student);
        model.addAttribute("grade", grade);
        model.addAttribute("updating", updating);
        return "form";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid @ModelAttribute("student") Student student,
                             BindingResult result,
                             @RequestParam(required = false) Long courseId,
                             @RequestParam(required = false) Integer newGrade) {

        if(result.hasErrors()){
            return "form";
        }
        String generatedEmail = student.getFirstName().toLowerCase() + "." + student.getLastName().toLowerCase() + "@student.uni.com";
        student.setEmail(generatedEmail);
        Course course = courseService.getCourse(courseId);
        if(courseId != null){
            student.getCourses().add(course);
            course.getStudents().add(student);
        }
        studentService.saveStudent(student);
        Grade gradeEntity = new Grade();
        gradeEntity.setStudent(student);
        gradeEntity.setCourse(course);
        gradeEntity.getScores().add(newGrade);
        gradeService.saveGrade(gradeEntity);

        return "redirect:/grades";
    }

    @PostMapping("/addCourse")
    public String submitCourse(@RequestParam(required = false) Long studentId,
                               @RequestParam(required = false) Long courseId,
                               @RequestParam(required = false) Integer newGrade) {
        Student student;
        if(studentId == null){
            throw new NullPointerException("Student ID not found");

        }
        student = studentService.getStudentById(studentId);
        Course course = courseService.getCourse(courseId);
        if(courseId != null){
            student.getCourses().add(course);
            course.getStudents().add(student);
        }
        studentService.saveStudent(student);
        Grade gradeEntity = new Grade();
        gradeEntity.setStudent(student);
        gradeEntity.setCourse(course);
        gradeEntity.getScores().add(newGrade);
        gradeService.saveGrade(gradeEntity);

        return "redirect:/grades";
    }

    @PostMapping("/submitGrade")
    public String submitGrade(@RequestParam(required = false) Long studentId,
                              @RequestParam(required = false) Long courseId,
                              @RequestParam(required = false) Long gradeId,
                              @RequestParam(required = false) Integer newGrade) {
        if(studentId == null){
            throw new NullPointerException("Student ID not found");

        }
        if(courseId == null){
            throw new NullPointerException("Course ID not found");
        }
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourse(courseId);
        Grade grade = gradeService.getGrade(gradeId);
        grade.getScores().add(newGrade);
        studentService.saveStudent(student);
        courseService.saveCourse(course);
        gradeService.saveGrade(grade);

        return "redirect:/grades";
    }

    @PostMapping("/updateGrade")
    public String updateGrade(@RequestParam(required = false) Long studentId,
                              @RequestParam(required = false) Long courseId,
                              @RequestParam(required = false) Long gradeId,
                              @RequestParam(required = false) Integer score,
                              @RequestParam(required = false) Integer newGrade) {
        if(studentId == null || courseId == null || gradeId == null || newGrade == null){
            throw new IllegalArgumentException("Required parameters are missing");
        }

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourse(courseId);
        Grade grade = gradeService.getGrade(gradeId);
        int index = grade.getScores().indexOf(score);

        if(index != -1){
            grade.getScores().set(index, newGrade);
        } else{
            throw new IllegalArgumentException("Score not found in grade");
        }

        studentService.saveStudent(student);
        courseService.saveCourse(course);
        gradeService.saveGrade(grade);

        return "redirect:/grades";
    }


    @GetMapping("/addGrade")
    public String addGrade(@RequestParam(required = false) Long studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        List<Course> courseList = courseService.getCourses();
        model.addAttribute("student", student);
        model.addAttribute("courses", courseList);
        return "addGradeForm";
    }

    @GetMapping("/addGradeToCourse")
    public String addGradeToCourse(@RequestParam(required = false) Long studentId, @RequestParam(required = false) Long courseId, @RequestParam(required = false) Long gradeId, Model model) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourse(courseId);
        Grade grade = gradeService.getGrade(gradeId);
        model.addAttribute("student", student);
        model.addAttribute("course", course);
        model.addAttribute("grade", grade);
        return "addGradeToCourseForm";
    }

    @GetMapping("/changeGrade")
    public String changeGrade(@RequestParam(required = false) Long studentId, @RequestParam(required = false) Long courseId, @RequestParam(required = false) Long gradeId, @RequestParam(required = false) Integer score, Model model) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourse(courseId);
        Grade grade = gradeService.getGrade(gradeId);
        Integer scoreToUpdate = grade.getScores().indexOf(score);

        model.addAttribute("student", student);
        model.addAttribute("course", course);
        model.addAttribute("grade", grade);
        model.addAttribute("score", scoreToUpdate);
        return "updateGradeForm";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        List<StudentGradeDTO> studentGrades = gradeService.getAllStudentGrades();
        model.addAttribute("studentGrades", studentGrades);
        return "grades";
    }
}
