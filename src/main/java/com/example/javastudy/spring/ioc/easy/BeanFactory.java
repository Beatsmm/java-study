package com.example.javastudy.spring.ioc.easy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    Map<String,BeanDefinition> factory = new ConcurrentHashMap<>();

    public Object getBean(String name){
        return factory.get(name).getBean();
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition){
        factory.put(name,beanDefinition);
    }
}
