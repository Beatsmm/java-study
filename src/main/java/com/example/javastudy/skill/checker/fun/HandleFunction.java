package com.example.javastudy.skill.checker.fun;

import com.example.javastudy.skill.checker.Condition;

import java.io.Serializable;


@FunctionalInterface
public interface HandleFunction extends Serializable {

    boolean apply(Object target, Condition<?> condition);


}
