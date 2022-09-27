package com.example.javastudy.designMode.observerMode.event;

// 定义抽象的事件接口，这个接口相当于群里面发布的通知
public abstract class DataEvent {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
