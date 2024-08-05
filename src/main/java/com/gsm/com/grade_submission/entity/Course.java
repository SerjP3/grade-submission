package com.gsm.com.grade_submission.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Course{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String subject;

    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", students=" + students +
                '}';
    }
}
