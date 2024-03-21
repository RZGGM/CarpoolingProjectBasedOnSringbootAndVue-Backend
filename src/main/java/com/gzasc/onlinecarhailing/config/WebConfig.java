package com.gzasc.onlinecarhailing.config;

import com.gzasc.onlinecarhailing.interceptor.UserCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    UserCheckInterceptor userCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userCheckInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login/**");
    }
}
