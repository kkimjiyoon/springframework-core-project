package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
@SpringJUnitConfig(classes = JavaConfig.class)
class StudentsTest {

    @Autowired
    private Students students;

    @Autowired
    private Scores scores;

    @BeforeEach
    void setUp() {
        students.load();
        scores.load();
    }

    @Test
    void load() {
        Collection<Student> studentList = students.findAll();
        assertFalse(studentList.isEmpty());
    }

    @Test
    void findAll() {
        Collection<Student> studentList = students.findAll();
        assertEquals(4, studentList.size());
    }

    @Test
    void merge() {
        List<Score> scoreList= scores.findAll();
        Collection<Student> studentList = students.findAll();

        students.merge(scoreList);

        for (Student student : studentList) {
            for (Score score : scoreList) {
                if (student.getSeq() == score.getStudentSeq()) {
                    assertEquals(score.getScore(), student.getScore().getScore());
                }
            }
        }
    }
}