package com.example.javastudy.spring.ioc.middle.factory;

public class BeanDefinition {
    // 把上个简单实现IOC容器的Object bean替换成Class,这样就可以把Bean的实例化操作放到容器里
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
