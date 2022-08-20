package com.cq.studyprocess.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * swagger配置
 *
 * @author 程崎
 * @since 2022/07/22
 */
@Configuration
@EnableOpenApi
public class Knife4jConfiguration {

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .groupName("程崎")
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("(?!/ApiError.*).*"))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(newHashSet());
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
    }


    /**
     * 配置Swagger信息=apiInfo
     *
     * @return {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("程崎", "https://gitee.com/cq2021", "2972084238@qq.com");

        return new ApiInfo(
                "学习进度项目",
                "学习进度",
                "1.0",
                "https://gitee.com/cq2021",
                contact,
                "仓库地址",
                "https://gitee.com/cq2021",
                new ArrayList<>());
    }

    /**
     * 支持的通讯协议集合
     *
     * @return {@link Set}<{@link String}>
     */
    private Set<String> newHashSet() {
        Set<String> set = new HashSet<>();
        set.add("https");
        set.add("http");
        return set;
    }
}