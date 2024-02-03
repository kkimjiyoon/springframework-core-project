package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.ExecutionTime;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

@Component
public class CsvDataLoadService implements DataLoadService {
    private final Scores scores;
    private final Students students;

    public CsvDataLoadService(Scores scores, Students students) {
        this.scores = scores;
        this.students = students;
    }
    @Override
    @ExecutionTime
    public void loadAndMerge() {
        scores.load();
        students.load();
        students.merge(scores.findAll());
    }
}
