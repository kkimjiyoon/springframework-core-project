package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    private Scores scores;
    @BeforeEach
    void setUp() {
        scores = CsvScores.getInstance();
        scores.load();
    }

    @Test
    void load() {
        List<Score> scoreList = scores.findAll();
        assertFalse(scoreList.isEmpty());

    }

    @Test
    void findAll() {
        List<Score> scoreList = scores.findAll();
        assertEquals(3, scoreList.size());

        Score first = scoreList.get(0);
        Score last = scoreList.get(scoreList.size() - 1);

        assertEquals(1, first.getStudentSeq());
        assertEquals(30, first.getScore());
        assertEquals(3, last.getStudentSeq());
        assertEquals(70, last.getScore());
    }

}