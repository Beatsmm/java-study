package com.example.javastudy.designMode.observerMode.event;

// Listener的顶级接口，为了抽象Listener存在(相当于群成员)
public interface MyListener {

    void onEvent(DataEvent event);
}
