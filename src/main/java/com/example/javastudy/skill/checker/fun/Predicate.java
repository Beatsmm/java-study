package com.example.javastudy.skill.checker.fun;

@FunctionalInterface
public interface Predicate<T> {

    /**
     * 断言操作
     */
    boolean apply(T target);
}
