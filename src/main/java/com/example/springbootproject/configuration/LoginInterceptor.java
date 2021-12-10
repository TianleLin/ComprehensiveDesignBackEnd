package com.example.springbootproject.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, accept, content-type, rexx");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//        String origin = request.getHeader("Origin");
//        response.setHeader("Access-Control-Allow-Origin", origin);  // 不能是通配符*
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }
}
