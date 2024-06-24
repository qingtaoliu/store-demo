package com.cy.store.config;

import com.cy.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    HandlerInterceptor loginInterceptor = new LoginInterceptor();

    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/web/register.html", "/web/login.html",
                        "/bootstrap3/**", "/css/**", "/images/**", "/js/**",
                        "/web/index.html", "/user/login", "/user/register", "/user/update",
                        "/district/**", "/products/**");
    }

}
