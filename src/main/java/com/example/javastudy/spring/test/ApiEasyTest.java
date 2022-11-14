package com.example.javastudy.spring.test;

import com.example.javastudy.spring.ioc.easy.BeanDefinition;
import com.example.javastudy.spring.ioc.easy.BeanFactory;

public class ApiEasyTest {

    public static void main(String[] args) {
        //1、初始化Factory
        BeanFactory factory = new BeanFactory();
        //2、注册bean到容器中去
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());

        factory.registerBeanDefinition("userService",beanDefinition);
        //3、获取bean
        UserService userService = (UserService) factory.getBean("userService");
        userService.query();



    }
}
