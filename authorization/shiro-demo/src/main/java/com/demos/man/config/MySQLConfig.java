package com.demos.man.config;


import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySQLConfig extends MySQL5Dialect {


    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
