package com.cq.studyprocess.config;

import com.cq.studyprocess.common.JacksonObjectMapper;
import com.cq.studyprocess.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    protected void addInterceptors(@NotNull InterceptorRegistry registry) {
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
}
