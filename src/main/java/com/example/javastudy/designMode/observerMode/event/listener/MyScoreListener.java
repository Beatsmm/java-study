package com.example.javastudy.designMode.observerMode.event.listener;

import com.example.javastudy.designMode.observerMode.event.DataEvent;
import com.example.javastudy.designMode.observerMode.event.MyListener;
import com.example.javastudy.designMode.observerMode.event.ScoreDataEvent;
import org.springframework.stereotype.Component;

@Component
public class MyScoreListener implements MyListener {
    @Override
    public void onEvent(DataEvent event) {
        if (event instanceof ScoreDataEvent){
            System.out.println("积分处理"+event.getMsg());
        }
    }
}
