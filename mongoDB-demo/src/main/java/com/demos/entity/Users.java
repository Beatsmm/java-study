package com.demos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class Users {


    /**
     * 自定义mongo，主键 加这个注解可自定义类型以及自定义自增规则，如果不加 插入数据会默认生成ObjectId 类型的_id字段
     * org.springframework.data.annotation.Id 包下，mongo库主键字段还是为_id (本文实体类中字段为为id，意思查询字段为_id，但查询结果_id会映射到实体对象id字段中）
     */
    @Id
    private Long id;

    private String name;

    private Integer age;
}
