package com.gsm.com.grade_submission.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"student_id", "course_id"})
            }
    )
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +

                '}';
    }

    public void addCourses(Course course) {
        courses.add(course);
    }

    public void setCourses(List<Course> courses, Course course) {
        courses.add(course);
        this.courses = courses;
    }
}
