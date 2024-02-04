package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringJUnitConfig(classes = JavaConfig.class)
class GradeQueryServiceTest {

    @Autowired
    private DataLoadService csvDataLoadService;

    @Autowired
    private GradeQueryService defaultGradeQueryService;

    @BeforeEach
    void setUp() {
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
        String studentName = "A";

        List<Score> scoreList = defaultGradeQueryService.getScoreByStudentName(studentName);

        assertFalse(scoreList.isEmpty());
        assertEquals(2, scoreList.size());

    }

    @Test
    void getScoreByStudentSeq() {
        Score actualScore = defaultGradeQueryService.getScoreByStudentSeq(1);

        assertThat(actualScore).usingRecursiveComparison().isEqualTo(new Score(1, 30));
    }
}