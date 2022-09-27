package com.example.javastudy.designMode.observerMode;

import com.example.javastudy.designMode.observerMode.event.DataEvent;
import com.example.javastudy.designMode.observerMode.event.MyListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// 创建管理者的实现类，相当于具体的是什么群（微信群或者飞书群）
@Component
public class LoopObserverImpl implements ObserverInterface<MyListener> {
    // 监听者的注册列表
    private List<MyListener> listenerList = new ArrayList<>();
    @Override
    public void registerListener(MyListener myListener) {
        listenerList.add(myListener);
    }

    @Override
    public void removeListener(MyListener myListener) {
        listenerList.remove(myListener);
    }

    @Override
    public void notifyListener(DataEvent event) {
        for (MyListener myListener : listenerList) {
            myListener.onEvent(event);
        }
    }
}
