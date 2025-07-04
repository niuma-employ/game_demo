package com.lyh.demo_game;

import com.lyh.demo_game.intercept.LoginIntercept;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan(basePackages = "com.lyh.demo_game.mapper")
public class DemoGameApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(DemoGameApplication.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercept())
        // 添加拦截路径，这里表示拦截所有路径
        .addPathPatterns("/**")
        .excludePathPatterns("/**/*.jpg") // 排除.jpg文件
        .excludePathPatterns("/**/*.jpeg") // 排除.jpeg文件
        .excludePathPatterns("/**/*.png") // 排除.png文件
        .excludePathPatterns("/**/*.gif") // 排除.gif文件
        .excludePathPatterns("/**/*.bmp") // 排除.bmp文件
        .excludePathPatterns("/**/*.webp") // 排除.webp文件
        .excludePathPatterns("/**/*.apk") // 排除.apk文件
        // 排除拦截路径，这里表示不拦截登录和静态资源
        .excludePathPatterns("/login")
        .excludePathPatterns("/normalUser/**"); // 放行所有普通用户相关接口
    }
}
