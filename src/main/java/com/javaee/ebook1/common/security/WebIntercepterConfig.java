package com.javaee.ebook1.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xuzihan
 * @version 1.0
 * @description: 拦截器配置（已停用）
 * @data 2021/4/2
 **/
@Configuration
public class WebIntercepterConfig implements WebMvcConfigurer {
    @Autowired
    UserIntercepter userIntercepter;

    @Autowired
    AdminIntercepter adminIntercepter;

    @Autowired
    AnyIntercepter anyIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userIntercepter)
                .addPathPatterns("/user/**");
//                .excludePathPatterns("/login","/regist","/image/**");
        registry.addInterceptor(adminIntercepter)
                .addPathPatterns("/admin/**");
        registry.addInterceptor(anyIntercepter)
                .addPathPatterns("/switch/**")
                .addPathPatterns("/switch");

    }


}
