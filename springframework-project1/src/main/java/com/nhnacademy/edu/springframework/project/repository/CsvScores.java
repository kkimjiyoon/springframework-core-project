package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvScores implements Scores {
    private final String filePath = "data/score.csv";
    private final List<Score> scoreList = new ArrayList<>();

    private CsvScores(){} // private 생성자로 외부에서 객체 생성을 막아야한다.

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    private static CsvScores csvScores;  // 단 1개만 존재해야 하는 객체의 인스턴스로 static 으로 선언
    public static Scores getInstance() {
        if (csvScores == null) {
            csvScores = new CsvScores();
        }
        return csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(CsvScores.class.getClassLoader().getResourceAsStream(filePath)))) {
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
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
