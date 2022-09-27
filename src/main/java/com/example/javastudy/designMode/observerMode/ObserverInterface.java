package com.example.javastudy.designMode.observerMode;

import com.example.javastudy.designMode.observerMode.event.DataEvent;
import com.example.javastudy.designMode.observerMode.event.MyListener;
// 这个接口相当于群（管理者）
public interface ObserverInterface<T>{
    // 注册监听器
    void registerListener(T t);

    // 移除监听者
    void removeListener(T t);

    // 通知监听者
    void notifyListener(DataEvent event);
}
