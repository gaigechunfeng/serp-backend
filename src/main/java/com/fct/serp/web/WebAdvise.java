package com.fct.serp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
@Slf4j
public class WebAdvise {

    @ExceptionHandler
    @ResponseBody
    public WebResponse<?> handeExceptions(Throwable e) {
        log.error(e.getMessage(), e);
        return new WebResponse<>(false, e.getMessage(), null, null);
    }
}
