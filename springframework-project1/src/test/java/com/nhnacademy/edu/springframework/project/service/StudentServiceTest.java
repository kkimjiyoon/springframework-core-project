package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void getPassedStudents() {
        DefaultStudentService studentService = new DefaultStudentService();

        Collection<Student> passedStudents = studentService.getPassedStudents();

        assertNotNull(passedStudents);

        for (Student student : passedStudents) {
            assertFalse(student.getScore().isFail());
        }
    }

    @Test
    void getStudentsOrderByScore() {
        DefaultStudentService studentService = new DefaultStudentService();

        Collection<Student> studentsOrderByScore = studentService.getStudentsOrderByScore();

        assertNotNull(studentsOrderByScore);

        List<Student> studentList = List.copyOf(studentsOrderByScore);
        for (int i = 0; i < studentList.size() - 1; i++) {
            assertTrue(studentList.get(i).getScore().getScore() >= studentList.get(i + 1).getScore().getScore());
        }
    }
}