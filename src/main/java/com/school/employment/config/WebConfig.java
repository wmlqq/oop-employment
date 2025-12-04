package com.school.employment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加视图控制器，用于直接将URL路径映射到视图名称，无需编写Controller
     * @param registry 视图控制器注册器
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 将根路径 "/" 映射到 "index.html"
        // "forward:/index.html" 表示服务器内部跳转，地址栏不会改变
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}