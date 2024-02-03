package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.annotation.ExecutionTime;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DefaultStudentService implements StudentService {
    private final Students students;
    public DefaultStudentService(Students students) {
        this.students = students;
    }
    @Override
    @ExecutionTime
    public Collection<Student> getPassedStudents() {

        return students.findAll().stream()
                .filter(student -> !student.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    @ExecutionTime
    public Collection<Student> getStudentsOrderByScore() {

        return students.findAll().stream().sorted().collect(Collectors.toList());
    }
}
