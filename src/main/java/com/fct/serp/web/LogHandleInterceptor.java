package com.fct.serp.web;

import com.fct.serp.entities.OperateLog;
import com.fct.serp.entities.User;
import com.fct.serp.services.LogService;
import com.fct.serp.util.Constants;
import com.fct.serp.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Component
@Slf4j
public class LogHandleInterceptor implements HandlerInterceptor {

    private LogService logService;

    @Autowired
    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(">> 开始处理请求：{}，参数：{}", request.getRequestURI(), request.getParameterMap());
        addLog(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private void addLog(HttpServletRequest request) {

        try {
            OperateLog operateLog = new OperateLog();
            operateLog.setParams(JsonUtil.toJson(request.getParameterMap()));
            User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_CURRENT_USER);
            operateLog.setUsername(user == null ? null :
                    user.getUsername());
            operateLog.setRequestUri(request.getRequestURI());
            operateLog.setCreateTime(new Date());
            operateLog.setCreateUser("SYS");

            logService.save(operateLog);
        } catch (Exception e) {
            log.error(">> 落日志表发生异常 {}", e.getMessage(), e);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        log.info(">> 处理{}请求结束", request.getRequestURI());
    }
}
