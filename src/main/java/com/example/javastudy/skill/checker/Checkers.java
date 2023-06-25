package com.example.javastudy.skill.checker;


/**
 * 参数校验工具
 */
public class Checkers {

    public static <T> Checker<T> lambdaCheck(){
        return new Checker<>();
    }
}
