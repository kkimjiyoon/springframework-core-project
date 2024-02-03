package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.CsvConfig;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class ConfigMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CsvConfig.class);

        DataLoadService csvDataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        csvDataLoadService.loadAndMerge();

        StudentService studentService = context.getBean("defaultStudentService", StudentService.class);

        GradeQueryService defaultGradeQueryService = context.getBean("defaultGradeQueryService", GradeQueryService.class);

        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        System.out.println(defaultGradeQueryService.getScoreByStudentName("B"));
        System.out.println(defaultGradeQueryService.getScoreByStudentSeq(2));

    }
}
