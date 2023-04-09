package com.demos.entity;


import lombok.Data;

@Data
public class UsersQueryForm {

    private Integer pageIndex;

    private Integer pageSize;

    private String name;
}
