package com.example.javastudy.skill.util.converter;

@FunctionalInterface
public interface BeanCopierUtilCallBack<S, T> {

    /**
     * 默认回调方法
     * @param s
     * @param t
     */
    void callBack(S t, T s);
}
