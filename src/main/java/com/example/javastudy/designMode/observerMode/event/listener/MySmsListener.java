package com.example.javastudy.designMode.observerMode.event.listener;

import com.example.javastudy.designMode.observerMode.event.DataEvent;
import com.example.javastudy.designMode.observerMode.event.MyListener;
import com.example.javastudy.designMode.observerMode.event.SmsDataEvent;
import org.springframework.stereotype.Component;

/**
 * MyListener的实现类，短信监听者
 */
@Component
public class MySmsListener implements MyListener {
    @Override
    public void onEvent(DataEvent dataEvent) {
        if (dataEvent instanceof SmsDataEvent) {
            //...省略短信处理逻辑
            System.out.println("短信处理");
        }
    }
}