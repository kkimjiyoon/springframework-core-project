package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JavaConfig.class)
class DataLoadServiceTest {

    @Autowired
    private DataLoadService csvDataLoadService;

    @Autowired
    private Scores scores;

    @Autowired
    private Students students;

    @Test
    void loadAndMerge() {
        csvDataLoadService.loadAndMerge();

        assertFalse(students.findAll().isEmpty());
        assertFalse(scores.findAll().isEmpty());

        for (Student student : students.findAll()) {
            assertNotNull(student.getScore());
            for (Score score : scores.findAll()) {
                if (student.getSeq() == score.getStudentSeq()) {
                    assertEquals(score.getScore(), student.getScore().getScore());
                }
            }
        }
    }
}