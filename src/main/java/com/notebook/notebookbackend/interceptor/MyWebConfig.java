package com.notebook.notebookbackend.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author Ck
 * 拦截器配置类
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Resource
    private LogInterceptor logInterceptor;


    /**
     * 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        注册日志信息
        registry.addInterceptor(logInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(teacherLoginInterceptor).addPathPatterns(teacherLoginPath).excludePathPatterns(teacherLoginExcludePath);
//        registry.addInterceptor(adminLoginInterceptor).addPathPatterns(adminLoginPath).excludePathPatterns(adminLoginExcludePath);
    }
}
