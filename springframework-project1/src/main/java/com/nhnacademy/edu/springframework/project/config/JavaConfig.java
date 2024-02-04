package com.nhnacademy.edu.springframework.project.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.project")
public class JavaConfig {
//    @Bean
//    public CsvDataLoadService csvDataLoadService() {
//        return new CsvDataLoadService();
//    }
//
//    @Bean
//    public DefaultGradeQueryService defaultGradeQueryService() {
//        return new DefaultGradeQueryService();
//    }
//
//    @Bean
//    public DefaultStudentService defaultStudentService() {
//        return new DefaultStudentService();
//    }

}
