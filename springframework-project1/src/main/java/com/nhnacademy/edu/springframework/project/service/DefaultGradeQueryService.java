package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultGradeQueryService implements GradeQueryService {

    @Override
    public List<Score> getScoreByStudentName(String name) {
        Students studentRepository = CsvStudents.getInstance();

        return studentRepository.findAll().stream().filter(student -> student.getName().equals(name)).map(Student::getScore).collect(Collectors.toList());
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {
        Scores scoreRepository = CsvScores.getInstance();

        return scoreRepository.findAll().stream().filter(score -> score.getStudentSeq() == seq).findFirst().orElse(null);
    }
}
