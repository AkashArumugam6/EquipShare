package com.csye6220.equipshare.configuration;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.csye6220.equipshare.model.User;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthConfig implements Filter {

    private static List<String> allowedURLs = List.of("/", "/login", "/adminlogin", "/logout", "/perform-login", "/signup", "/register");
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        User user = (User) request.getSession().getAttribute("user");
        
        String requestURI = request.getRequestURI();
        if(!allowedURLs.contains(requestURI)){
            if(user == null){
                response.sendRedirect("/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
