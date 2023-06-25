package com.example.javastudy.skill.checker.fun;

import java.io.Serializable;


@FunctionalInterface
public interface SFunction<T, R> extends Serializable {

    /**
     *  执行方法
     */
    R apply(T t);
}
