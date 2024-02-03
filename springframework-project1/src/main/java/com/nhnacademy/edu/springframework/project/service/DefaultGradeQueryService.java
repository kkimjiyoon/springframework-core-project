package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.ExecutionTime;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultGradeQueryService implements GradeQueryService {

    private final Scores scores;
    private final Students students;

    public DefaultGradeQueryService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }
    @Override
    @ExecutionTime
    public List<Score> getScoreByStudentName(String name) {

        return students.findAll().stream().filter(student -> student.getName().equals(name)).map(Student::getScore).collect(Collectors.toList());
    }

    @Override
    @ExecutionTime
    public Score getScoreByStudentSeq(int seq) {

        return scores.findAll().stream().filter(score -> score.getStudentSeq() == seq).findFirst().orElse(null);
    }
}
