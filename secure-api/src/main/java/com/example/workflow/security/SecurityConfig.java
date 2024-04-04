package com.example.workflow.security;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilter() {
        FilterRegistrationBean<ProcessEngineAuthenticationFilter> registration = new FilterRegistrationBean<>();
        registration.setName("camunda-auth");
        registration.setFilter(this.getProcessEngineAuthenticationFilter());
        registration
                .addInitParameter("authentication-provider", ApiKeyAuthenticationProvider.class.getName());
        registration.addUrlPatterns("/engine-rest/*");
        return registration;
    }

    @Bean
    public ProcessEngineAuthenticationFilter getProcessEngineAuthenticationFilter() {
        return new ProcessEngineAuthenticationFilter();
    }
}
