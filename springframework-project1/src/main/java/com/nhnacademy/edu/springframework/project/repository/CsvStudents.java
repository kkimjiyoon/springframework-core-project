package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CsvStudents implements Students {

    private final String filePath = "data/student.csv";
    private final List<Student> studentList = new ArrayList<>();

    private CsvStudents() {}

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    private static CsvStudents csvStudents;
    public static Students getInstance() {
        if (csvStudents == null) {
            csvStudents = new CsvStudents();
        }
        return csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
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

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
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