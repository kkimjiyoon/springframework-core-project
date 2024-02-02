package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.Score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvTest {
    public static void main(String[] args) {
        String filePath = "data/score.csv";
        List<Score> scoreList = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(CsvTest.class.getClassLoader().getResourceAsStream(filePath)))) {
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                String[] scoreArray = line.split(",");
                int studentSeq = Integer.parseInt(scoreArray[0]);
                int score = Integer.parseInt(scoreArray[1]);

                scoreList.add(new Score(studentSeq, score));
            }

            for(Score s : scoreList) {
                System.out.println(s.getStudentSeq());
                System.out.println(s.getScore());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
