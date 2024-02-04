package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = JavaConfig.class)
class StudentServiceTest {

    @Autowired
    private DataLoadService csvDataLoadService;

    @Autowired
    private StudentService studentService;
    @Test
    void getPassedStudents() {

        Collection<Student> passedStudents = studentService.getPassedStudents();

        assertNotNull(passedStudents);

        for (Student student : passedStudents) {
            assertFalse(student.getScore().isFail());
        }
    }

    @Test
    void getStudentsOrderByScore() {
        Collection<Student> studentsOrderByScore = studentService.getStudentsOrderByScore();

        assertNotNull(studentsOrderByScore);

        List<Student> studentList = List.copyOf(studentsOrderByScore);
        for (int i = 0; i < studentList.size() - 1; i++) {
            assertTrue(studentList.get(i).getScore().getScore() >= studentList.get(i + 1).getScore().getScore());
        }
    }
}