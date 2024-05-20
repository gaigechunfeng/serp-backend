package com.fct.serp.config;

import com.fct.serp.web.LogHandleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.*;

import java.time.Duration;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private LogHandleInterceptor logHandleInterceptor;

    @Autowired
    public void setLogHandleInterceptor(LogHandleInterceptor logHandleInterceptor) {
        this.logHandleInterceptor = logHandleInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(Duration.ofDays(300)));

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8000")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(logHandleInterceptor);
    }
}
