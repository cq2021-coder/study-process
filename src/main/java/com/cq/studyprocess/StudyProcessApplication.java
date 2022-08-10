package com.cq.studyprocess;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 研究流程应用程序
 *
 * @author 程崎
 * @since 2022/08/10
 */
@SpringBootApplication
@MapperScan("com.cq.studyprocess.mapper")
@Slf4j
@EnableCaching
public class StudyProcessApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(StudyProcessApplication.class);
        ConfigurableEnvironment environment = springApplication.run(args).getEnvironment();
        log.info("success start！");
        String port = environment.getProperty("server.port");
        log.info("knife4j的网址为\thttp://localhost:{}/doc.html", port);

    }

}
