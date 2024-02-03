package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvScores implements Scores {
    private final String filePath = "data/score.csv";
    private final List<Score> scoreList = new ArrayList<>();

    private CsvScores() {
    }
    private static CsvScores csvScores;

    public static Scores getInstance() {
        if (csvScores == null) {
            csvScores = new CsvScores();
        }
        return csvScores;
    }

    @Override
    public void load() {
        try (InputStream inputStream = new ClassPathResource(filePath).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] scoreArray = line.split(",");
                int studentSeq = Integer.parseInt(scoreArray[0]);
                int score = Integer.parseInt(scoreArray[1]);

                scoreList.add(new Score(studentSeq, score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
