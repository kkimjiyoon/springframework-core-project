package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CsvStudents implements Students {

    private final String filePath = "data/student.csv";
    private final List<Student> studentList = new ArrayList<>();

    private CsvStudents() {}
    private static CsvStudents csvStudents;
    public static Students getInstance() {
        if (csvStudents == null) {
            csvStudents = new CsvStudents();
        }
        return csvStudents;
    }

    @Override
    public void load() {
        try(InputStream inputStream = new ClassPathResource(filePath).getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                String[] studentArray = line.split(",");
                int studentSeq = Integer.parseInt(studentArray[0]);
                String name = studentArray[1];

                studentList.add(new Student(studentSeq, name));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    @Override
    public void merge(Collection<Score> scores) {
        for (Student student : studentList) {
            List<Score> mergeScoreList = scores.stream().filter(score -> score.getStudentSeq() == student.getSeq()).collect(Collectors.toList());

            for (Score score : mergeScoreList) {
                student.setScore(score);
            }
        }
    }
}