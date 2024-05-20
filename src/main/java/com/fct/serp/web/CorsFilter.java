package com.fct.serp.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@WebFilter
public class CorsFilter implements Filter {

    @Value("${isDev:false}")
    private boolean isDev;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (isDev) {
            res.setHeader("Access-Control-Allow-Origin", "http://localhost:8000");
            res.setHeader("Access-Control-Allow-Credentials", "true");
        }
        chain.doFilter(request, response);
    }
}
