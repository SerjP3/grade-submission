package com.gsm.com.grade_submission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Grade{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private List<Integer> scores = new ArrayList<>();

    @NonNull
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


}
