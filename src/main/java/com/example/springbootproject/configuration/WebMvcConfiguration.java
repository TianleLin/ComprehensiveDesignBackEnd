package com.example.springbootproject.configuration;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("/image/avatar/**").addResourceLocations("file:E:/tempFile/pictures/avatar/");
        registry.addResourceHandler("/image/avatar/**").addResourceLocations("file:/home/ubuntu/pictures/avatar/");

//        registry.addResourceHandler("/video/**").addResourceLocations("file:E:/tempFile/videos/");
        registry.addResourceHandler("/video/**").addResourceLocations("file:/home/ubuntu/video/");

//        registry.addResourceHandler("/slide/**").addResourceLocations("file:E:/tempFile/slides/");
        registry.addResourceHandler("/slide/**").addResourceLocations("file:/home/ubuntu/slide/");

//        registry.addResourceHandler("/compressed/**").addResourceLocations("file:E:/tempFile/compressed/");
        registry.addResourceHandler("/compressed/**").addResourceLocations("file:/home/ubuntu/compressed/");

        registry.addResourceHandler("/pdf/**").addResourceLocations("file:/home/ubuntu/pdf/");
//        registry.addResourceHandler("/pdf/**").addResourceLocations("file:E:/tempFile/pdf/");

        registry.addResourceHandler("/file/**").addResourceLocations("file:/home/ubuntu/file/");
//        registry.addResourceHandler("/file/**").addResourceLocations("file:E:/tempFile/file/");

//        registry.addResourceHandler("/web/**").addResourceLocations("file:E:/VUEProjects/");

        registry.addResourceHandler("/homework/**").addResourceLocations("file:/home/ubuntu/homework/");
//        registry.addResourceHandler("/homework/**").addResourceLocations("file:E:/tempFile/homework/");

        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   //所有方法都做处理跨域
                .allowedOrigins("http://localhost:8080")  //允许跨域的请求头
//                .allowedOrigins("*")  //允许跨域的请求头
                .allowedMethods("*")  //允许通过地请求方法
                .allowedHeaders("*");  //允许的请求头
    }


}
