package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class GradeQueryServiceTest {
    @BeforeEach
    void setUp() {
        CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
        DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();

        String studentName = "A";

        List<Score> scoreList = defaultGradeQueryService.getScoreByStudentName(studentName);

        assertFalse(scoreList.isEmpty());

        for (Score score : scoreList) {
            assertTrue(score.getScore() == 30 || score.getScore() == 70);
        }

    }

    @Test
    void getScoreByStudentSeq() {
        DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();

        Score actualScore = defaultGradeQueryService.getScoreByStudentSeq(1);

        assertThat(actualScore).usingRecursiveComparison().isEqualTo(new Score(1, 30));
    }
}