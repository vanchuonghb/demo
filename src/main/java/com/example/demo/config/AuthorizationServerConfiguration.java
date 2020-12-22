//package com.example.demo.config;
//
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthorizationServerConfiguration implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException exception) throws IOException, ServletException {
//        SecurityUtils.sendError(response, exception, HttpServletResponse.SC_UNAUTHORIZED,
//                "Authentication failed");
//    }
//
//}
//
