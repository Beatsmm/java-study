package com.example.javastudy.skill.sign;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public RequestFilter requestCachingFilter() {
        return new RequestFilter();
    }

    @Bean
    public FilterRegistrationBean requestCachingFilterRegistration(
            RequestFilter requestFilter) {
        FilterRegistrationBean bean = new FilterRegistrationBean(requestFilter);
        bean.setOrder(1);
        return bean;
    }

}
