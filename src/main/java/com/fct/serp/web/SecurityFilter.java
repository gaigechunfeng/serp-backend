package com.fct.serp.web;

import com.fct.serp.util.Constants;
import com.fct.serp.util.JsonUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        if (uri.startsWith("/static/")
                || uri.equals("/user/login")
                || uri.equals("/user/current")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession();
        if (session.getAttribute(Constants.SESSION_KEY_CURRENT_USER) == null) {

            res.setContentType("text/json;charset=utf-8");
            res.getWriter().print(JsonUtil.toJson(WebResponse.error("您还没有登录，请先登录")));
            res.flushBuffer();
            return;
        }

        chain.doFilter(request, response);
    }
}
