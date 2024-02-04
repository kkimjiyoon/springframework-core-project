package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.config.JavaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringJUnitConfig(classes = JavaConfig.class)
class ScoresTest {

    @Autowired
    private Scores scores;

    @BeforeEach
    void setUp() {
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
        assertEquals(4, scoreList.size());

        Score first = scoreList.get(0);
        Score last = scoreList.get(scoreList.size() - 1);

        assertEquals(1, first.getStudentSeq());
        assertEquals(30, first.getScore());
        assertEquals(4, last.getStudentSeq());
        assertEquals(55, last.getScore());
    }

}