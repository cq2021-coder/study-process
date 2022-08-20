package com.cq.studyprocess.config;

import com.cq.studyprocess.common.JacksonObjectMapper;
import com.cq.studyprocess.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

import static com.cq.studyprocess.constants.EnvironmentConstant.PROD;

/**
 * web mvc配置
 *
 * @author 程崎
 * @since 2022/08/06
 */
@Configuration
@Slf4j
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    protected void addInterceptors(@NotNull InterceptorRegistry registry) {
        if (active.equals(PROD)){
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns(
                            "/user/login",
                            "/user/register"
                    );
        }else {
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns(
                            "/doc.html",
                            "/webjars/**",
                            "/swagger-resources/**",
                            "/v3/**",
                            "/user/login",
                            "/user/register"
                    );
        }
    }

    /**
     * 添加资源处理程序
     *
     * @param registry 注册表
     */
    @Override
    protected void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 扩展消息转换器
     *
     * @param converters 转换器
     */
    @Override
    protected void extendMessageConverters(@NotNull List<HttpMessageConverter<?>> converters) {
        //设置消息转换器
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        converters.add(0, messageConverter);

    }

    /**
     * 配置跨域
     *
     * @param registry 注册表
     */
    @Override
    protected void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "http://localhost:5173",
                        "http://127.0.0.1:5173",
                        "http://localhost:8080",
                        "http://127.0.0.1:8080",
                        "http://120.48.83.118",
                        "http://81.69.247.89",
                        "http://cqlovehwh.com",
                        "http://www.cqlovehwh.com",
                        "https://www.cqlovehwh.com"
                )
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                .allowCredentials(true)
                // No need to send pre-check again within 1 hour
                .maxAge(3600);
    }
}
