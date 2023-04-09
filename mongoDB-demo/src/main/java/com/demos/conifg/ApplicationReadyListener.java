package com.demos.conifg;


import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import javax.annotation.Resource;

// 如果这个类不加的话那么插入的一行会默认添加一个_class字段来存储实体类类型 比较(com.demos.entity.User)
@Configuration
public class ApplicationReadyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private MongoTemplate mongoTemplate;

    private static final String TYPEKEY = "_class";


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        MongoConverter converter = mongoTemplate.getConverter();
        if (converter.getTypeMapper().isTypeKey(TYPEKEY)){
            ((MappingMongoConverter) converter).setTypeMapper(new DefaultMongoTypeMapper(null));
        }
    }
}
