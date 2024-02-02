package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {
        CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
        csvDataLoadService.loadAndMerge();

        Students students = CsvStudents.getInstance();
        Scores scores = CsvScores.getInstance();

        assertFalse(students.findAll().isEmpty());
        assertFalse(scores.findAll().isEmpty());

        for (Student student : students.findAll()) {
            assertNotNull(student.getScore());
            assertEquals(scores.findAll().stream().filter(score -> score.getStudentSeq() == student.getSeq()).findFirst().orElse(null), student.getScore());
        }
    }
}