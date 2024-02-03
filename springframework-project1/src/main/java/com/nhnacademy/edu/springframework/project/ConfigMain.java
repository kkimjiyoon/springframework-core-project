package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.CsvConfig;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class ConfigMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CsvConfig.class);

        CsvDataLoadService csvDataLoadService = context.getBean("csvDataLoadService", CsvDataLoadService.class);
        csvDataLoadService.loadAndMerge();

        DefaultStudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);

        DefaultGradeQueryService defaultGradeQueryService = context.getBean("defaultGradeQueryService", DefaultGradeQueryService.class);

        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        System.out.println(defaultGradeQueryService.getScoreByStudentName("B"));
        System.out.println(defaultGradeQueryService.getScoreByStudentSeq(2));

    }
}
