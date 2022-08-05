package com.cq.studyprocess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cq.studyprocess.mapper")
public class StudyProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProcessApplication.class, args);
    }

}
